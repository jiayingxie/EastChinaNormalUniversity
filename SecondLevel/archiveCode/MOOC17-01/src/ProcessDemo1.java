
public class ProcessDemo1 {

	public static void main(String[] args) {
		while(true)
		{
			int a = (int) (Math.random() * 100);
			System.out.println(" main thread is running " + a);
			try {
				Thread.sleep(5000); //5000∫¡√Î
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
}
