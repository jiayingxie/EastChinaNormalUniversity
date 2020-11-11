
public class DateUtil {
	public boolean isLeapYear(int year) {
		if(year <= 0 || year > 10000) return false;
		if ((year % 100 != 0 && year % 4 ==0) || (year % 400 == 0)) return true;
		return false;
	}
}
