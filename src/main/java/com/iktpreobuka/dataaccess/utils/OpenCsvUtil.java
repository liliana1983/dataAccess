package com.iktpreobuka.dataaccess.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.iktpreobuka.dataaccess.entities.UserEntity;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class OpenCsvUtil {
	public static List<UserEntity> parseCsvFile(InputStream is){
		String [] CSV_HEADER= {"name","email"};
		Reader fileReader=null;
		CsvToBean <UserEntity> csvToBean=null;
		List<UserEntity> users= new ArrayList<UserEntity>();
		try {
			fileReader= new InputStreamReader(is);
			ColumnPositionMappingStrategy<UserEntity> mappingStrategy= new ColumnPositionMappingStrategy<>();
			mappingStrategy.setType(UserEntity.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);
			csvToBean= new CsvToBeanBuilder<UserEntity>(fileReader).withMappingStrategy(mappingStrategy).build();
			users= csvToBean.parse();
			return users;
		}catch (Exception e) {
			System.out.println("Reading CSV ERROR!");
			e.printStackTrace();
		}finally {
			try {
				fileReader.close();
			}catch (IOException e) {
				System.out.println("Closing fileReader Error");
				e.printStackTrace();
			}
		}
		return users;
		
	}
public static void usersToCsvFile (Writer writer,List<UserEntity> users) {
		String [] CSV_HEADER= {"id","name","email","date_of_birth"};
		StatefulBeanToCsv<UserEntity> beanToCsv=null;
		try {
			//ispisi listu korisnika
			ColumnPositionMappingStrategy<UserEntity> mappingStrategy= new ColumnPositionMappingStrategy<>();
			mappingStrategy.setType(UserEntity.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);
			beanToCsv= new StatefulBeanToCsvBuilder<UserEntity>(writer).withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).build();
			beanToCsv.write(users);
		}catch (Exception e) {
			System.out.println("writing CSV error!");
			e.printStackTrace();
		}

}
}
