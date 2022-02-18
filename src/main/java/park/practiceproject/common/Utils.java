package park.practiceproject.common;

import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Slf4j
public class Utils {
	private static Utils instance = null;
	private Random rnd = SecureRandom.getInstanceStrong();

	public Utils() throws NoSuchAlgorithmException {
		// Random 초기화시 발생 될 수 있는 NoSuchAlgorithmException를 위한 생성자
	}

	public static synchronized Utils getInstance() {
		if (instance == null) {
			synchronized(Utils.class) {
				Lazy<Utils> lazyObjectSupplier = Lazy.of(() -> {
					try {
						return new Utils();
					} catch (NoSuchAlgorithmException e) {
						log.error(getPrintStackTrace(e));
					}

					return null;
				});

				instance = lazyObjectSupplier.get();
			}
		}

		return instance;
	}

	public Random rnd() {
		return rnd;
	}

	public String makeMD5(String str) {
		String result = null;

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(str.getBytes());

			byte[] byteData = md.digest();

			StringBuilder sb = new StringBuilder();

			for(int i = 0 ; i < byteData.length ; i++) {
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}

			result = sb.toString();
		} catch(NoSuchAlgorithmException e) {
			log.error(getPrintStackTrace(e));
		}

		return result;
	}

	public static String getPrintStackTrace(Exception e) {
		StringWriter errors = new StringWriter();

		e.printStackTrace(new PrintWriter(errors));

		return errors.toString();
	}
}
