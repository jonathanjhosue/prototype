package com.company.prototype.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;
import java.util.Locale;



public class ApplicationConfiguration {
	
	static List<String> countries = new ArrayList<String>();
	static List<String> currenciesISO = new ArrayList<String>();
	
	
	String[] viaLocales = { "JPY", "CNY", "SDG", "RON", "MKD", "MXN", "CAD",
		    "ZAR", "AUD", "NOK", "ILS", "ISK", "SYP", "LYD", "UYU", "YER", "CSD",
		    "EEK", "THB", "IDR", "LBP", "AED", "BOB", "QAR", "BHD", "HNL", "HRK",
		    "COP", "ALL", "DKK", "MYR", "SEK", "RSD", "BGN", "DOP", "KRW", "LVL",
		    "VEF", "CZK", "TND", "KWD", "VND", "JOD", "NZD", "PAB", "CLP", "PEN",
		    "GBP", "DZD", "CHF", "RUB", "UAH", "ARS", "SAR", "EGP", "INR", "PYG",
		    "TWD", "TRY", "BAM", "OMR", "SGD", "MAD", "BYR", "NIO", "HKD", "LTL",
		    "SKK", "GTQ", "BRL", "EUR", "HUF", "IQD", "CRC", "PHP", "SVC", "PLN",
		    "USD" };
		String[] iso4217WithoutViaLocales = { "XBB", "XBC", "XBD", "UGX", "MOP",
		    "SHP", "TTD", "UYI", "KGS", "DJF", "BTN", "XBA", "HTG", "BBD", "XAU",
		    "FKP", "MWK", "PGK", "XCD", "COU", "RWF", "NGN", "BSD", "XTS", "TMT",
		    "GEL", "VUV", "FJD", "MVR", "AZN", "MNT", "MGA", "WST", "KMF", "GNF",
		    "SBD", "BDT", "MMK", "TJS", "CVE", "MDL", "KES", "SRD", "LRD", "MUR",
		    "CDF", "BMD", "USN", "CUP", "USS", "GMD", "UZS", "CUC", "ZMK", "NPR",
		    "NAD", "LAK", "SZL", "XDR", "BND", "TZS", "MXV", "LSL", "KYD", "LKR",
		    "ANG", "PKR", "SLL", "SCR", "GHS", "ERN", "BOV", "GIP", "IRR", "XPT",
		    "BWP", "XFU", "CLF", "ETB", "STD", "XXX", "XPD", "AMD", "XPF", "JMD",
		    "MRO", "BIF", "CHW", "ZWL", "AWG", "MZN", "CHE", "XOF", "KZT", "BZD",
		    "XAG", "KHR", "XAF", "GYD", "AFN", "SOS", "TOP", "AOA", "KPW" };
	
	static{
		String[] locales = Locale.getISOCountries();
		for (String countryCode : locales) {
			 
			Locale obj = new Locale("", countryCode);	 
			countries.add(obj.getDisplayCountry());
		}
		
		Locale[] locs = Locale.getAvailableLocales();
		
		for(Locale loc : locs) {
            try {             
            	String val=Currency.getInstance( loc ).getCurrencyCode();
            	 if(!currenciesISO.contains(val))
            		 currenciesISO.add(val);
                
            } catch(Exception exc)
            {
                // Locale not found
            }
        }
		
	}
	
	
	public static enum TiposIdentificacion{
		CEDULA_FISICA, CEDULA_JURIDICA, PASAPORTE, RESIDENCIA, OTRA
	}
	
	
	public static enum TiposCliente{
		TARJETAHABIENTE, CADENA_COMERCIOS, COMERCIO
	}
	
	public static enum TiposCuenta{
		DEBITO, CREDITO
	}
	
	public static enum TiposTarjeta{
		DEBITO, CREDITO
	}
	

	public static enum TiposComision{
		MARCA, EMISOR, BANCO, HACIENDA, SISTEMA, OTRO
	}
	
	public static enum TiposTransaccion{
		COMPRA               ("01"), DEPOSITO   ("02"), RETIRO   ("03"),
		TRANSFERENCIA_INTERNA("04"), 
		TRANSFERENCIA_EXTERNA("05"), SIMPE      ("06"), REEMBOLSO("07"), 
		ERROR                ("-1"), DESCONOCIDO("00");
		
		private final String value;
		
		TiposTransaccion(String value){
			this.value=value;
		}
		
		public final String getValue(){
			return value;
		}
		
		public static TiposTransaccion parseTiposTransaccion(String value){
			
			for(int i=0;i<TiposTransaccion.values().length;i++){
				TiposTransaccion t=TiposTransaccion.values()[i];
				
				if(t.value.equals(value)){
					return t;					
				}			
			}
			return TiposTransaccion.DESCONOCIDO;
		}
	}
	
	public static enum EstadosTransaccion{
		ANULADA    ("-1"),  RECHAZADA("-2"),
		DESCONOCIDO("00"), 
		RECIBIDA   ("01"),  PROCESADA("02"), 
		LIQUIDADA  ("03");
		
		private final String value;
		
		EstadosTransaccion(String value){
			this.value=value;
		}
		
		public final String getValue(){
			return value;
		}
	}
	
	
	public static enum EstadosEstadoCuenta{
		ACTIVO                  ("A"),  
		INACTIVO                ("I"), 
		MOVIMIENTOS_PENDIENTES  ("MP"),
		TRANSACCIONES_PENDIENTES("TP"),  
		DESCONOCIDO("D");
		
		private final String value;
		
		EstadosEstadoCuenta(String value){
			this.value=value;
		}
		
		public final String getValue(){
			return value;
		}
	}
	
	
	public static enum EstadosEstandar{
		ACTIVO("A"), INACTIVO("I");
		
		private final String value;
		
		EstadosEstandar(String value){
			this.value=value;
		}
		
		public String getValue(){
			return value;
		}
		
	}
	
	public static List<String> getCountries(){
		return countries;
	}
	
	
	public static List<String> getISOCountries(){
		return currenciesISO;
	}
	
	
	
	public static enum AuthorizerResponse{
		SUCCESSFUL   ("01"), 
		INVALID_TYPE ("02"), 
		INVALID_DATA ("03"),
		REJECTED("04"), 
		
		
		TRANSFERENCIA_EXTERNA("05"), 
		SIMPE      ("06"), 
		REEMBOLSO            ("07"), 
		ERROR                ("08"), 
		DESCONOCIDO("00");
		
		private final String value;
		
		AuthorizerResponse(String value){
			this.value=value;
		}
		
		public final String getValue(){
			return value;
		}
		
		
	}
	
	
	public static enum ExportTypeFormat{
		PDF(".pdf"),ODT(".odt"),DOCX(".docx"),XLSX(".xlsx"),PPT(".ppt");
		
		private final String value;
		
		ExportTypeFormat(String value){
			this.value=value;
		}
		
		public final String getValue(){
			return value;
		}
		
		
	}
	
	

}
