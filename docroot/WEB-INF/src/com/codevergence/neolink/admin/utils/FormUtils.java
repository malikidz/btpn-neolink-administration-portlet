package com.codevergence.neolink.admin.utils;

import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;

public class FormUtils {
	public static boolean isSelected(String value, String reference){
		boolean selected = false;
		if(Validator.isNotNull(value) && value.equals(reference)){
			selected = true;
		}
		return selected;
	}
	
	public static boolean isSelected(long value, long reference){
		return isSelected(String.valueOf(value), String.valueOf(reference));
	}
	
	public static boolean isSelected(boolean reference){
		boolean selected = false;		
		if(Validator.isNotNull(reference) && (reference == true)){
			selected = true;
		}
		return selected;
	}
	
	public static Calendar getCalendar(Date referenceDate){
		Calendar cal = CalendarFactoryUtil.getCalendar();
		if(referenceDate != null){
			cal.setTime(referenceDate);
		}
		return cal;
	}
}
