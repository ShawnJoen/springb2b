package com.spring.util;

import java.util.Date;

public class Common {
	
	public long getTimeStamps() {
		
		return new Date().getTime();
	}
	
	public long getTimeStampsLength10() {
		
		return Long.parseLong(
				String.valueOf(
						this.getTimeStamps()
						).substring(0, 10)
				);
	}

}
