package com.iktpreobuka.dataaccess.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.csv.CSVParser;

import com.iktpreobuka.dataaccess.entities.UserEntity;

public class ApacheCommonsCsvUtil {
	private static String csvExtension = "csv";

	public static ByteArrayInputStream usersToCsv(List<UserEntity> users) throws IOException {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out),
					CSVFormat.DEFAULT.withHeader("id", "name", "email", "date_of_birth"));
			{
				for (UserEntity user : users) {
					List<String> data = Arrays.asList(String.valueOf(user.getId()), String.valueOf(user.getName()),
							String.valueOf(user.getEmail()), String.valueOf(user.getDateOfBirth()));
					csvPrinter.printRecord(data);
				}
			}
			csvPrinter.close();// csvPrinter.flush(); nije hteo da mi zatvori csvPrinter pa dodah close umesto
								// flush!
			return new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			System.out.println("writing CSV error!");
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}

	public static List<UserEntity> parseCsvFile(InputStream is) {
		BufferedReader fileReader = null;
		CSVParser csvParser = null;
		List<UserEntity> users = new ArrayList<>();
		try {
			fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			csvParser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord csvRecord : csvRecords) {
				UserEntity user = new UserEntity(Integer.parseInt(csvRecord.get("id")), csvRecord.get("email"),
						csvRecord.get("name"), LocalDate.parse(csvRecord.get("date_of_birth")));
				users.add(user);
			}

		} catch (Exception e) {
			System.out.println("Reading CSV error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvParser.close();
			} catch (IOException e) {
				System.out.println("closing fileReader or csvParser Error!");
				e.printStackTrace();
			}
		}
		return users;
	}

//metoda koja proverava da li je fajl csv ekstenzije 
	public static boolean isCSVFile(MultipartFile file) {
		String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
		// String extension = file.getOriginalFilename().split(csvExtension)[1]; // 0 mi
		// daje ime fajla a 1 ekstenziju , a mogla sam koristiti
		// org.springframework.util.StringUtils
		// String extension =
		// StringUtils.getFilenameExtension(file.getOriginalFilename());
		if (!extension.equals(csvExtension)) {
			return false;
		}
		return true;

	}
}
