import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/*
 * use java project will appear: Can't find bundle for base name 
 * msg,
 * we need to put resources folder to the class path,
 * select project(SecondChap03TextHandling), right click,
 * properties, find Libraries, add class folder
 * */
public class TextHandling {
	public static void main(String[] args) {
		// 输出university和name在zh_CN, en_NZ，en_US三种Locale的值
		Locale myLocale = Locale.getDefault();
		Locale secondLocale = new Locale("en", "NZ");
		Locale thirdLocale = new Locale("en", "US");
		
		ResourceBundle rb = ResourceBundle.getBundle("msg", myLocale);
		System.out.println(myLocale + ", university: "+ rb.getString("university"));
		System.out.println(myLocale + ", name: "+ rb.getString("name"));
		
		rb = ResourceBundle.getBundle("msg", secondLocale);
		System.out.println(secondLocale + ", university: "+ rb.getString("university"));
		System.out.println(secondLocale + ", name: "+ rb.getString("name"));
		
		rb = ResourceBundle.getBundle("msg", thirdLocale);
		try {
			System.out.println(thirdLocale + ", university: "+ rb.getString("university"));
		} catch (MissingResourceException e) {
			System.out.println(thirdLocale + ", university has exception:");
			e.printStackTrace();
		}
		System.out.println(thirdLocale + ", name: "+ rb.getString("name"));		
	}
}
