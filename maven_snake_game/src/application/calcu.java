package application;

import java.util.Scanner;


public class calcu {
	//дадлага  2018 - 2022
	public static  int dadlagaDays1(int startYear, int endYear ) {
		int count = 0 ;
		int dadlagaMin = 0 ;
		for (int year = startYear ; year <= endYear ; year++) {
			count++;	
		}
		dadlagaMin = ((count * 40 ) * 8 ) * 60 ;
		
		
		return dadlagaMin;
	}
	//дадлага  1988 - 1993
		public static  int dadlagaDays2(int startYear, int endYear ) {
			int count = 0 ;
			int dadlagaMin = 0 ;
			for (int year = startYear ; year <= endYear ; year++) {
				count++;	
			}
			dadlagaMin = (((count * 40 ) * 8 ) * 60)  + (((count * 8 ) * 6 ) * 60 );
			
			
			return dadlagaMin;
		}
		
	
	//ажлын өдрийн тоолох 2018-2022
	public static int workingDays(int year , int month) {
		int workingday = 0;
		int daysInMonth = daysInMonth(year , month);
		for(int day = 1 ; day <= daysInMonth ; day++) {
			int dayOfWeek = dayOfWeek(year , month , day);
			if(dayOfWeek != 0 && dayOfWeek != 6 ) {
				workingday ++;
				
			}
		}
		return workingday;
		
	}
	//ажлын өдрийн тоолох 1988-1993
		public static int workingDays1(int year , int month) {
			int workingday = 0;
			int daysInMonth = daysInMonth(year , month);
			for(int day = 1 ; day <= daysInMonth ; day++) {
				int dayOfWeek = dayOfWeek(year , month , day);
				if(dayOfWeek != 0 ) {
					workingday ++;
					
				}
			}
			return workingday;
			
		}
	
	//ажлын өдрийг тооцох 2018-2022
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
	
	//ажлын өдрийг тооцох 2 1988-1993
		public static int WorkDay1(int startYear , int endYear) {
			int ajilsnOdor = 0 ;
			int ajilsnOdor1 = 0;
			for (int year = startYear ; year <= endYear - 1 ; year++) {
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
		//амралтын өдөр тооцоолох 
		 public static boolean isWeekend(int year, int month, int day) {
		        int dayOfWeek = dayOfWeek(year, month, day);
		        return dayOfWeek == 6 || dayOfWeek == 0; // Saturday is 6, Sunday is 0
	
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
	 //2006 оноос 2022 хүртэл 
	 public static int baga(int startYear, int endYear) {
		int studyMin = 0 ;
		
		studyMin = (WorkDay(startYear , endYear) * 4 ) * 30 ;
		
		return studyMin;
	 }
	 public static int ahlah(int startYear , int endYear) {
		 int studyMin = 0 ;
		  return studyMin = (WorkDay(startYear , endYear) * 6 )*35;
		 
	 }
	 public static int deed(int startYear , int endYear) {
		 int studyMin = 0 ;
		 int count = endYear- startYear;
		  return studyMin = ((WorkDay(startYear , endYear)-(count*40)) * 3)*90;
		 
	 }
	 
	 
	 //1978 - 1993 
	 public static int baga1 (int startYear , int endYear) {
		 int studyMin = 0 ;
			
			studyMin = ((WorkDay1(startYear , endYear) * 6 )*34) * 45 ;
			
			return studyMin;
	 } 
	 public static int dund1(int startYear , int endYear) {
		 int studyMin = 0 ;
		  return studyMin = ((WorkDay1(startYear , endYear) /6 )*34)*45;
		 
	 }
	 public static int ahlah1(int startYear , int endYear) {
		 int studyMin = 0 ;
		  return studyMin = (WorkDay1(startYear , endYear) * 6 )*45;
		 
	 }
	 public static int deed1(int startYear , int endYear) {
		 int studyMin = 0 ;
		 int count = endYear-startYear;
		  return studyMin = ((WorkDay1(startYear , endYear)-(count*48)) * 3) *90;
		 
	 }
	 public static int sonirholtoitsag1(int startYear , int endYear) {
		 int sortsag = 0 ;
		 return sortsag = ((WorkDay1(startYear , endYear)/6)*4) * 60;
	 }
	 
	 public static int sonirholtoitsag(int startYear , int endYear) {
		 int sortsag = 0 ;
		 return sortsag = ((WorkDay(startYear , endYear)/5)*4) * 60;
	 }
	 
	 
	 public static void asuult1() {
		 int deedSursnTsag1 =  deed(2018 , 2022) -  dadlagaDays1(2018 , 2022) ;
		 int deedSursnTsag2 = deed1(1988 , 1993) -  dadlagaDays2(1988 , 1993);
		 int ih = 0;
		 int dund_sur1 = baga(2006 , 2011)+ ahlah(2011 , 2018) ;
		 
		 int dund_sur2 = baga1(1978 , 1981) + ahlah1(1981 , 1986) + dund1(1986 , 1988);
		 
		 
	     int deed_sur1 = deed(2018 , 2022);
	    
		 int deed_sur2 = deed1(1988 , 1993);
		 
		 
		 if (dund_sur1 > dund_sur2) {
			 System.out.println("2006 - 2018 онуудын дунд сургуулийн суралцах цаг их байна");
			 
		 }else {
			 System.out.println("1978 - 1988 онуудын дунд сургуулийн суралцах цаг их байна");
		 }
		 
		 if (deed_sur1 > deed_sur2) {
			 System.out.println("2006 - 2018 онуудын дээд сургуулийн суралцах цаг их байна");
			 
		 }else {
			 System.out.println("1988 - 1993 онуудын дээд сургуулийн суралцах цаг их байна");
		 }
		 System.out.println("Дунд сур нийт сурсан хугацаа(2006-2018) " + dund_sur1);
		 System.out.println("Дунд сур нийт сурсан хугацаа(1978-1988) " + dund_sur2 );
		 System.out.println("Их сур нийт сурсан хугацаа(2018-2022) " +deed_sur1);
		 System.out.println("Их сур нийт сурсан хугацаа(1988-1993) " +deed_sur2);
		 System.out.println( "2006 - 2011 (1-5 анги)оны хооронд ажлын 5 өдөр 4 цаг хичээллэсэн минут = " + baga(2006 , 2011));
	     System.out.println( "2011 - 2018 (6-12 анги)оны хооронд ажлын 5 өдөр 6 цаг хичээллэсэн минут = " + ahlah(2011 , 2018));
	     System.out.println( "2018 - 2022(их сургууль) оны хооронд ажлын 5 өдөр 3 цаг хичээллэсэн минут = "  + deedSursnTsag1 );
	     
	     
	     

	     System.out.println( "1978 - 1981(1-3 анги) оны хооронд ажлын 6 өдөр 4 цаг хичээллэсэн минут = " + baga1(1978 , 1981));
	     System.out.println( "1981 - 1986(4-8 анги) оны хооронд ажлын 6 өдөр 4 цаг хичээллэсэн минут = " + ahlah1(1981 , 1986));
	     System.out.println( "1986 - 1988 (9-10 анги)оны хооронд ажлын 6 өдөр 6 цаг хичээллэсэн минут = " + dund1(1986 , 1988));
	     System.out.println( "1988 - 1993 (их сургууль)оны хооронд ажлын 6 өдөр 3 цаг хичээллэсэн минут = " + deedSursnTsag2);
		
	 }
	 public static void asuult2() {
		 
		 int dund_sur1 = baga(2006 , 2011)+ ahlah(2011 , 2018) ;
		 int dund_sur2 = baga1(1978 , 1981) + ahlah1(1981 , 1986) + dund1(1986 , 1988);
		 
		 int dund_sor1 = sonirholtoitsag1(2006 , 2018);
		 int dund_sor2 = sonirholtoitsag(1978 , 1988);
		 
		
		
		  if (dund_sor2 < dund_sor1) {
			  System.out.println("2006 - 2018 он  сонирхолтой хичээл үзсэн цаг " + dund_sor2 + " их байна");  
		  }
		  else {
			  System.out.println("2006 - 2018 он  сонирхолтой хичээл үзсэн цаг "  + dund_sor1 + " их байна");
			  
		  }
		  System.out.println("2006 - 2018 он  сонирхолтой хичээл үзсэн цаг " + dund_sor2 + " хичээл үзсэн цаг " + dund_sur1);
			 System.out.println("1978 - 1988 он  сонирхолтой хичээл үзсэн цаг " + dund_sor1 + " хичээл үзсэн цаг " + dund_sur2);
		  
		 
	 }
	 //Хэрэв дээд сургуульд оюутан бүр хичээлийн дундуур 1 удаа 8 долоо хоногийн
	// үйлдвэрлэлийн дадлага хийдэг байсан бол дадлагын цагийн суралцах хугацаанд нь эзлэх
	 //хувийг тус тус олно уу.
	
	 public static void asuuult3() {
		 double a= ((double)dadlagaDays1(2018, 2022)/deed(2018, 2022))*100;
		 String formattedResult= String.format("%.2f", a);
		 System.out.println("2018 - 2022 Дээд сургуулийн сурсан хугацааны дундуур дадлага хийсэн хугацааы харьцуулалт  "  + formattedResult + "% байна ") ;
		 
		 
		 int deedSursnTsag2 = deed1(1988 , 1993) -  dadlagaDays2(1988 , 1993);
		 double b = ((double) dadlagaDays2(1988 , 1993 ) / deed1(1988, 1993)) * 100  ;
			String formattedResult2 = String.format("%.2f", b);
			 System.out.println("1988 - 1993 Дээд сургуулийн сурсан хугацааны дундуур дадлага хийсэн хугацааы харьцуулалт  "  + formattedResult2 + "% байна ") ;
		 
		 
		 
	 }
	 
public static void main(String[] args) {
		
	        Scanner sc = new Scanner(System.in);
	        int choice = 0;
	        boolean ch = true;
	        while (ch) {
	            System.out.println("1.Суралцсан хугацааны харьцуулалт");
	            System.out.println("2.Сонирхсон хичээлийн цагийн харьцуулалт");
	            System.out.println("3.Дадлагийн цагийн эзлэх хувь");
	            System.out.println("4.Таны оруулсан мэдээлэл байхгүй байна.");
	            choice = sc.nextInt();
	            switch (choice) {
	                case 1:
	                    System.out.println("1.Суралцсан хугацааны харьцуулалт");
	                    asuult1();
	                    break;
	                case 2:
	                    System.out.println("2.Сонирхсон хичээлийн цагийн харьцуулалт");
	                    asuult2();
	                    break;
	                case 3:
	                    System.out.println("3.Дадлагийн цагийн эзлэх хувь");
	                    asuuult3();
	                    break;
	                case 4:
	                    System.out.println("4.Гарах");
	                    ch = false;
	                    break;
	            }
	        }
	        sc.close();
	    }
	}

	
