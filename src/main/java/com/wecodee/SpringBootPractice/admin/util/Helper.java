package com.wecodee.SpringBootPractice.admin.util;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecodee.SpringBootPractice.admin.config.JwtRequestFilter;
import com.wecodee.SpringBootPractice.admin.repository.FunctionDataRepository;

public class Helper {

	private static String SECRET_KEY = "wecodeeinnovations";
	private static String SALT = "wecodeeinnovations";
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FunctionDataRepository functionDataRepository;

	public static Date getCurrentDateTime() {
		return new Date(new Date().getTime());
	}

	public static String getActiveUser() {
		return JwtRequestFilter.loggedInUserId;
	}

	public static Date getCurrentDateAndTime() {
		return new Date();
	}

	public static Integer getNextVersion(Integer currentVersion) {
		return currentVersion == null ? 1 : currentVersion + 1;
	}

	public static Date getCurrentDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = sdf.parse(sdf.format(new Date()));
			return date;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LocalDate getCurrentLocalDate() {
		try {
			return LocalDate.now();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static LocalDateTime getCurrentLocalDateAndTime() {
		try {
			return LocalDateTime.now();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String localDateToString(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return localDate.format(formatter);
	}

	public static String formatDateAsString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Object getObjectFromFunctionData(String functionJsonData, Object obj)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		Object object = mapper.readValue(functionJsonData, obj.getClass());
		return object;
	}

	public static long calculateDiffrenceBetweenDates(Date firstDate1, Date secondDate1) throws ParseException {
		DateFormat dateFormate = new SimpleDateFormat("MM/dd/yyyy");
		String date1 = dateFormate.format(firstDate1);
		String date2 = dateFormate.format(secondDate1);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
		Date firstDate = sdf.parse(date1);
		Date secondDate = sdf.parse(date2);
		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		return diff;
	}

	public static Date formattingDate(Date fromDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fromDate = sdf.parse(sdf.format(fromDate));
			return fromDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date formattingDateAndTime(Date fromDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		try {
			fromDate = sdf.parse(sdf.format(fromDate));
			return fromDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String formatDateAndTimeAsString(Date fromDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		try {
			return sdf.format(fromDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date convertStringDateAndTimeAsDate(String dateAndTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
		try {
			return sdf.parse(dateAndTime);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int getCurrentMinute() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("Hmm");
		String formateToString = dateFormat.format(new Date());
		return Integer.valueOf(formateToString);
	}

	public static int getYear(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		String formatToString = dateFormat.format(date);
		return Integer.valueOf(formatToString);
	}

	public static String formatDateToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);
		return strDate;
	}

	public static Date formatDateToString1(Date date) throws ParseException {
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		String formattedToStr = formatter1.format(date);
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date formDate = formatter.parse(formattedToStr);
		System.out.println("formatted to date" + formDate);
		return formDate;
	}

	public static Boolean containsValue(String value) {
		if (value == null) {
			return false;
		} else if (value.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	private <T> String getFieldValue(String fieldName, T object) {
		Field field;
		try {
			field = object.getClass().getDeclaredField(fieldName);
//            return field.getName();
		} catch (NoSuchFieldException e) {
			log.error("" + e);
			return null;// or ""
		}
		field.setAccessible(true);
		try {
			return (String) field.get(object);
		} catch (IllegalAccessException e) {
			log.error("" + e);
			return null;// or ""
		}
	}

	public static String encryption(String strToEncrypt) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			IvParameterSpec ivspec = new IvParameterSpec(iv);
			// Create SecretKeyFactory object
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			// Create KeySpec object and assign with
			// constructor
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey temp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(temp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			// Return encrypted string
			String encryptedString = Base64.getEncoder()
					.encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
			System.out.println(" encrypted string = " + encryptedString);
			return encryptedString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decryption(String strToDecrypt) {
		try {
			byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			// Create IvParameterSpec object and assign with
			// constructor
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			// Create SecretKeyFactory Object
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

			// Create KeySpec object and assign with
			// constructor
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			// AES/CBC/PKCS5Padding
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			// Return decrypted string
			String decryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
			System.out.println(" decrypted string " + decryptedString);
			return decryptedString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String convertIntegerToString(int i) {
		return String.valueOf(i);
	}

	public static String formatZoneDateAndTime(ZonedDateTime zdt) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedZdt = zdt.format(formatter);
		System.out.println(formattedZdt);
		return formattedZdt;
	}

}
