package application;

public class calcu {
    public static String Oyutan2018(int startYear) {
		
		switch(startYear) {
		case 2019 : case 2020 :case 2021 : case 2022:
			return "Оюутан";
		case 2018:
			return "Оюутан";
		default:
			return "Сурагч";
		}
		
	}
	
	public static String Oyutan1988(int startYear) {
		
		switch(startYear) {
		case 1993 :case 1989 : case 1990 :case 1991 : case 1992 :
			
			return "Оюутан";
		case 1988:
			return "Оюутан";
		default:
			return "Сурагч";
		}
		
	}
	
	
		
	//ажлын өдрийн тоолох 2018 - 2022
	public static int workingDays(int year , int month) {
		int workingday = 0;
		int daysInMonth = daysInMonth(year , month);
		for(int day = 1 ; day <= daysInMonth ; day++) {
			int dayOfWeek = dayOfWeek(year , month , day);
			if(dayOfWeek >= 1 && dayOfWeek <= 5 ) {
				workingday ++;
				
			}
		}
		return workingday;
		
	}
	//ажлын өдрийн тоолох  1988 - 1993
	public static int workingDays1(int year, int month) {
        int workingday = 0;
        int daysInMonth = daysInMonth(month, year);
        for (int day = 1; day <= daysInMonth; day++) {
            int dayOfWeek = dayOfWeek(year, month, day);
            if (dayOfWeek >= 1 && dayOfWeek <= 6) {
                workingday++;
            }
        }
        return workingday;
        

    }
	
	//ажлын өдрийг тооцох 2018 - 2022
	public static int WorkDay(int startYear , int endYear) {
		int ajilsnOdor = 0 ;
		int ajilsnOdor1 = 0;
		for (int year = startYear ; year <= endYear - 1 ; year++) {
			for (int month = 9 ; month <= 12 ; month++) {
				ajilsnOdor += workingDays(year , month);
				 
			}
		}
		for (int year = startYear + 1 ; year <= endYear ; year++) {
			for (int month = 1 ; month <= 5 ; month++) {
				ajilsnOdor1 += workingDays(year , month);
				  
			}
		}
		
	return (ajilsnOdor+ajilsnOdor1);
	}
	
	//ажлын өдрийг тооцох  1988 - 1993
		public static int WorkDay1(int startYear , int endYear) {
			int ajilsnOdor = 0 ;
			int ajilsnOdor1 = 0;
			for (int year = startYear ; year <= endYear -1 ; year++) {
				for (int month = 9 ; month <= 12 ; month++) {
					ajilsnOdor += workingDays1(year , month);
					 
				}
			}
			for (int year = startYear + 1 ; year <= endYear ; year++) {
				for (int month = 1 ; month <= 5 ; month++) {
					ajilsnOdor1 += workingDays1(year , month);
					  
				}
			}
			
		return (ajilsnOdor+ajilsnOdor1);
		}
		
		

	
	//жилийн тоо 
	public static int daysInYear(int year) {
		return isLeapYear(year) ? 366 : 365;
		
	}
	// сарын өдөр тооцоолох
	public static int daysInMonth(int month, int year) {
        switch (month) {
            case 2: 
                return isLeapYear(year) ? 29 : 28;
            case 4: case 6: case 9: case 11: // April, June, September, November
                return 30;
            default:
                return 31;
        }
        
    }
	//1988 - 1993 амралтын өдөр тооцох
	public static int Amralt(int startYear , int endYear) {
		int amraltOdor = 0 ;
		int amraltOdor1 = 0;
		for (int year = startYear ; year <= endYear -1 ; year++) {
			for (int month = 9 ; month <= 12 ; month++) {
				amraltOdor += daysInMonth(month , year)- workingDays1(year , month);
				 
			}
		}
		for (int year = startYear + 1 ; year <= endYear ; year++) {
			for (int month = 1 ; month <= 5 ; month++) {
				amraltOdor1+= daysInMonth(month , year)- workingDays1(year , month);
				  
			}
		}
		
	return (amraltOdor+amraltOdor1);
	}
	//2006 - 2018 амралтын өдөр тооцох
		public static int Amralt1(int startYear , int endYear) {
			int amraltOdor = 0 ;
			int amraltOdor1 = 0;
			for (int year = startYear ; year <= endYear -1 ; year++) {
				for (int month = 9 ; month <= 12 ; month++) {
					amraltOdor += daysInMonth(month , year)- workingDays(year , month);
					 
				}
			}
			for (int year = startYear + 1 ; year <= endYear ; year++) {
				for (int month = 1 ; month <= 5 ; month++) {
					amraltOdor1+= daysInMonth(month , year)- workingDays(year , month);
					  
				}
			}
			
		return (amraltOdor+amraltOdor1);
		}
	
	//өндөр жил тооцоолох
	public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
	//амралтын өдөр тооцоолох
	 public static int dayOfWeek (int year , int month , int day ) {
	int y = year - (14 - month)/12;
	int m = month + 12 * ((14 - month) / 12) - 2;
    int d = (day + y + y / 4 - y / 100 + y / 400 + (31 * m) / 12) % 7;
    return d;
	 }
	 //1978 - 1993
	 public static String angi(int year) {
		 switch(year) {
		 case 1978:
			 return "1";
		 case 1979:
			 return "2";
		 case 1980:
			 return "3";
		 case 1981:
			 return "4";
		 case 1982:
			 return "5";
		 case 1983:
			 return "6";
		 case 1984:
			 return "7";
		 case 1985:
			 return "8";
		 case 1986:
			 return "9";
		 case 1987:
			 return "10";
		 
			 default:
				 return "-";
		 }
		 
	 }
	 //2006 - 2018
	 public static String angi1(int year) {
		 switch(year) {
		 case 2006:
			 return "1";
		 case 2007:
			 return "2";
		 case 2008:
			 return "3";
		 case 2009:
			 return "4";
		 case 2010:
			 return "5";
		 case 2011:
			 return "6";
		 case 2012:
			 return "7";
		 case 2013:
			 return "8";
		 case 2014:
			 return "9";
		 case 2015:
			 return "10";
		 case 2016:
			 return "11";
		 case 2017:
			 return "12";
			 
		 
			 default:
				 return "-";
		 }
		 
	 }
	 
	//2006 - 2018
	 public static String hicheelTsag1(int year) {
		 switch(year) {
		 case 2006:case 2007:case 2008: case 2009: case 2010:
			 return "20";
		 case 2011: case 2012:case 2013: case 2014:case 2015: case 2016:case 2017:
			 return "30";
		 case 2018: case 2019: case 2020:case 2021:case 2022:
			 return"15";
			
			 default:
				 return "-";
		 }
		 
	 }
	 
	//2006 - 2018
		 public static String hicheelminut1(int year) {
			 switch(year) {
			 case 2006:case 2007:case 2008: case 2009: case 2010:
				 return "30";
			 case 2011: case 2012:case 2013: case 2014:case 2015: case 2016: case 2017:
				 return "35";
			 case 2018: case 2019: case 2020:case 2021:case 2022:
				 return"90";
				
				 default:
					 return "-";
			 }
			 
		 }
	 //1978 - 1993
	 public static String hicheelTsag(int year) {
		 switch(year) {
		 case 1978:case 1979:case 1980:
			 return "24";
		 case 1981:case 1982:case 1983: case 1984:case 1985:
			 return "34";
		 case 1986: case 1987:
			 return"36";
			 case 1988: case 1989: case 1990: case 1991:case 1992:
		 case 1993:
			 return "18";
			 default:
				 return "-";
		 }
		 
	 }
	 //1978 - 1993
	 public static String hicheelminut(int year) {
		 switch(year) {
		 case 1978:case 1979:case 1980:
			
		 case 1981:case 1982:case 1983: case 1984:case 1985:
			 
		 case 1986: case 1987:
		 return "45";
		 case 1988:
		  case 1989: case 1990: case 1991:case 1992:
		 case 1993:
			 return "90";
			 default:
				 return "-";
		 }
		 
	 }
	 
	//2006 - 2018
		 public static String dadlaga1(int year) {
			 switch(year) {
			
			  case 2018: case 2019: case 2020:case 2021:
			 case 2022:
				 return "40 цаг";
				 default:
					 return "-";
			 } }
	 
	//1989 - 1993 
	 public static String dadlaga(int year) {
		 switch(year) {
		 case 1988:
		  case 1989: case 1990: case 1991:case 1992:
		 case 1993:
			 return "46 цаг";
			 default:
				 return "-";
		 } }
	 
	 public static void asuult2()
	 {
		  
		int day = WorkDay1(1978 ,1988) / 7 ;
		System.out.println("         -1978 - 1988 он хүртэл " + day +" н 7 хоног байна үүнд 7 хоног бүр 4н цаг сонирхолтой хичээл үзсэн гэвэл " + day * 4 + " цаг " );
		int day1 = WorkDay1(1978 , 1981)/7;
		int day2 = WorkDay1(1981 , 1986)/7;
		int day3 = WorkDay1(1986 , 1988)/7;
		int tsag1 = day1 * 18 + day2 * 25 + day3 * 27;
		System.out.println("         -1978 - 1988 он хүртэл " + day +" н 7 хоног байна үүнд 7 хоног бүр хичээлийн цагийг тооцвол " + tsag1 + " цаг " );
	 }  
	 public static void asuult21() {
		 int day = WorkDay(2006 ,2018) / 7 ;
			System.out.println("         -2006 - 2018 он хүртэл " + day +" н 7 хоног байна үүнд 7 хоног бүр 4н цаг сонирхолтой хичээл үзсэн гэвэл " + day * 4 + " цаг " );
			int day1 = WorkDay(2006 , 2011)/7;
			int day2 = WorkDay(2011 , 2018)/7;
			
			int tsag1 = day1 * 10 + day2 * 17 ;
			System.out.println("         -2006 - 2018 он хүртэл " + day +" н 7 хоног байна үүнд 7 хоног бүр хичээлийн цагийг тооцвол " + tsag1 + " цаг " );
		 
			
	 }
	 public static void asuult3() {
		 double dadlaga = 46;
     System.out.println("       - Оюутан 5 н жилийн хугацаанд " + dadlaga*5 + " цаг дадлага хийнэ");
	 double day = 	 WorkDay1(1988 , 1993) / 7 - 5 ;
	 double hari =0;
	 hari=((dadlaga*5) / (day * 27)) * 100 ;
	 System.out.println("       - Оюутан 5 н жилийн хугацаанд " + day * 27 + " цаг суралцана ");
	 System.out.println("       - Оюутан 5 н жилийн хугацаанд дадлага хийсэн цагийн харьцуулалт нь " +hari+ "% байна");
	 }
	 public static void asuult31() {
		 double dadlaga = 40;
     System.out.println("       - Оюутан 4 н жилийн хугацаанд " + dadlaga*4 + " цаг дадлага хийнэ");
	 double day = 	 WorkDay(2018 , 2022) / 7 - 4 ;
	 double hari = ((dadlaga*4) / (day * 22)) * 100 ;
	 System.out.println("       - Оюутан 4 н жилийн хугацаанд " + day * 22 + " цаг суралцана ");
	 System.out.println("       - Оюутан 4 н жилийн хугацаанд дадлага хийсэн цагийн харьцуулалт нь " +hari+ "% байна");
	 }
	 
	 public static void main(String[] args) {

		
            int startYear = 1978 ; 
             int endYear = 1992;
	        
 	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
	        System.out.printf("| %8s | %-5s | %-4s | %-8s | %-9s | %-7s | %-15s | %-10s | %-12s |%-13s |%-8s |%n",
	                          "Он", "Статус", "Анги", "Амралт", "Хичээл", "Ажлын ", "Хичээлийн цаг", "1 хичээл", "7 хоногт", "7 хоногт(цаг)","Дадлага","");
	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

	        // Outer loop for rows
	      
	            	for(int a = startYear ; a <= endYear ; a ++) {
	            		int nextYear = a + 1;
	            		
	                System.out.printf("| %8s| %-5s | %-4s | %-8s | %-9s | %-7s | %-15s | %-10s | %-12s |%-13s |%-8s |%n",
	                                  a +"-"+ nextYear ,  Oyutan1988(a) , angi(a) , Amralt(a , nextYear)+" хоног", WorkDay1(a , nextYear)+" хоног", 6 +"өдөр", hicheelTsag(a)+"(7 хоногт)", hicheelminut(a)+" (минут)",Integer.parseInt( hicheelTsag(a))* Integer.parseInt(hicheelminut(a))+" (минут)",( Integer.parseInt( hicheelTsag(a))* Integer.parseInt(hicheelminut(a)))/60+" (цаг)",dadlaga(a));
	            	}
	    	        System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
	        
	        
	           
System.out.println("1р асуулт");
System.out.println("    - 1978 аас 1988 оны хооронд 10 жил суралцсан хугацааг 7 хоногийн байдлаар тооцоолвол 233 цаг");
System.out.println("    - 1988 аас 1993 онд суралцсан хугацааг 7 хоногоор тооцоолвол 135 цаг");

System.out.println("2р асуулт");
asuult2();

System.out.println("3р асуулт");
asuult3();

int startYear1 = 2006 ; 
int endYear1 = 2021;

System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
System.out.printf("| %8s | %-5s | %-4s | %-8s | %-9s | %-7s | %-15s | %-10s | %-12s |%-13s |%-8s |%n",
                 "Он", "Статус", "Анги", "Амралт", "Хичээл", "Ажлын ", "Хичээлийн цаг", "1 хичээл", "7 хоногт", "7 хоногт(цаг)","Дадлага","");
System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

// Outer loop for rows

   	for(int a = startYear1 ; a <= endYear1 ; a ++) {
   		int nextYear1 = a + 1;
   		
       System.out.printf("| %8s| %-5s | %-4s | %-8s | %-9s | %-7s | %-15s | %-10s | %-12s |%-13s |%-8s |%n",
                         a +"-"+ nextYear1 ,  Oyutan2018(a) , angi1(a) , Amralt1(a , nextYear1)+" хоног", WorkDay(a , nextYear1)+" хоног", 5 +"өдөр", hicheelTsag1(a)+"(7 хоногт)", hicheelminut1(a)+" (минут)",Integer.parseInt( hicheelTsag1(a))* Integer.parseInt(hicheelminut1(a))+" (минут)",( Integer.parseInt( hicheelTsag1(a))* Integer.parseInt(hicheelminut1(a)))/60+" (цаг)",dadlaga1(a));
   	}
       System.out.println("------------------------------------------------------------------------------------------------------------------------------------");   
       System.out.println("1р асуулт");
       System.out.println("    - 2006 аас 2018 оны хооронд 12 жил суралцсан хугацааг 7 хоногийн байдлаар тооцоолвол 169 цаг");
       System.out.println("    - 2018 аас 2022 онд суралцсан хугацааг 7 хоногоор тооцоолвол 88 цаг");
       System.out.println("2р асуулт");
       asuult21();
       
       System.out.println("3р асуулт");
       asuult31();
    }
    

}