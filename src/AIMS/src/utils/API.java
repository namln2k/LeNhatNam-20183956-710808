package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Class gửi http request và nhận dữ liệu
 * 
 * @author NAM.LN183956
 * @version 1.0
 */
public class API {

	/**
	 * Định dạng format cho ngày tháng
	 */
	public static DateFormat DATE_FORMATER = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * Đối tượng ghi log cho hệ thống
	 */
	private static Logger LOGGER = Utils.getLogger(Utils.class.getName());

	/**
	 * Phương thức gửi các request GET và nhận response
	 * 
	 * @param url:   Đường dẫn đến server
	 * @param token: Mã xác thực người dùng
	 * @return response: Phản hồi từ server (Kiểu String)
	 * @throws Exception
	 * @author NAM.LN183956
	 */
	public static String get(String url, String token) throws Exception {
		// Ghi log về request
		LOGGER.info("Request URL: " + url + "\n");

		// Khởi tạo và thiết lập kết nối
		HttpURLConnection conn = createConnection(url);
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Authorization", "Bearer " + token);
		
		// Đọc phản hồi từ server
		String response = readResponse(conn);
		
		// Ghi log về response
		LOGGER.info("Respone Info: " + response);
		
		// Trả về response (Kiểu String)
		return response;
	}

	/**
	 * Phương thức gửi các request dạng POST và nhận response
	 * 
	 * @param url:  Đường dẫn đến server
	 * @param data: Dữ liệu gửi lên server (Kiểu json)
	 * @return response: Phản hồi từ server (Kiểu String)
	 * @throws IOException
	 * @author NAM.LN183956
	 */
	public static String post(String url, String data) throws IOException {
		// Thiết lập phương thức được cho phép là PATCH
		allowMethods("PATCH");
		
		// Dữ liệu gửi đi
		String payload = data;
		
		// Ghi log về request
		LOGGER.info("Request Info:\nRequest URL: " + url + "\n" + "Payload Data: " + payload + "\n");

		// Khởi tạo và thiết lập kết nối
		HttpURLConnection conn = createConnection(url);
		conn.setRequestMethod("PATCH");
		
		// Gửi dữ liệu
		Writer writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
		writer.write(payload);
		writer.close();
		
		// Đọc phản hồi từ server
		String response = readResponse(conn);
		
		// Ghi log về response
		LOGGER.info("Respone Info: " + response);
		
		// Trả về response (Kiểu String)
		return response;
	}
	
	/**
	 * Khởi tạo kết nối tới server để gửi các request và nhận các response
	 * @param url: Đường dẫn tới server
	 * @return conn: Kết nối được khởi tạo
	 * @throws IOException
	 * @throws ProtocolException
	 * @author NAM.LN183956
	 */
	private static HttpURLConnection createConnection(String url) throws IOException {
		// Đường dẫn đến server
		URL apiURL = new URL(url);
			
		// Khởi tạo kết nối
		HttpURLConnection conn = (HttpURLConnection) apiURL.openConnection();
		
		// Thiết lập các cài đặt cho request
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/json");
		
		// Trả về kết nối
		return conn;
	}

	/**
	 * Đọc phản hồi từ server qua kết nối
	 * 
	 * @param conn: Kết nối tới server
	 * @return response: Phản hồi từ server (Kiểu String)
	 * @throws IOException
	 * @author NAM.LN183956
	 */
	private static String readResponse(HttpURLConnection conn) throws IOException {
		// Khởi tạo BufferedReader để đọc phản hồi
		BufferedReader in;
		
		if (conn.getResponseCode() / 100 == 2) {
			// Nếu response trả về mã thành công (2xx)
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			// Nếu response trả về mã thất bại
			in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		
		// Xây dựng String để ghi phản hồi
		StringBuilder response = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);
		in.close();
		
		// Trả về response (Kiểu String)
		return response.toString();
	}

	/**
	 * Thiết lập các phương thức được cho phép (POST, PUT, PATCH...)
	 * 
	 * @param methods: Danh sách các phương thức được cho phép
	 */
	private static void allowMethods(String... methods) {
		try {
			// Lấy danh sách phương thức trong connection
			Field methodsField = HttpURLConnection.class.getDeclaredField("methods");
			methodsField.setAccessible(true);

			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

			// Tạo danh sách các phương thức được cho phép khi gọi hàm này 
			String[] oldMethods = (String[]) methodsField.get(null);
			Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
			methodsSet.addAll(Arrays.asList(methods));
			String[] newMethods = methodsSet.toArray(new String[0]);

			// Gán các phương thức
			methodsField.set(null/* static field */, newMethods);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

}
