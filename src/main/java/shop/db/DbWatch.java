package shop.db;


public class DbWatch {

	private long first = 0;
	private long second = 0;

	public DbWatch() {
		first = System.currentTimeMillis();
	}

	public void getPool() {
		second = System.currentTimeMillis();
	}

	public void commit(String procName) {
		long end = System.currentTimeMillis();		
		long spendTime = end - first;
		long getTime = second - first;
		if (spendTime > 1000) {
			System.out.println("procName=" + procName + " getTime=" + getTime + " spendTime=" + spendTime);
		}
	}
}
