package com.company.prototype.service;


import com.company.prototype.model.entity.Tarjeta;
import java.util.ArrayList;
import java.util.List;

public class TarjetasServiceTest {
	
	
	private List<Tarjeta> list = new ArrayList<Tarjeta>();
	
	
	public List<Tarjeta> createTarjetas(int n){
		
		for(int i=0;i<n;i++){
			
			Tarjeta t= new Tarjeta();
			/*t.setNumero("44561237891234"+(i+1000));
                        Entidad e= new Entidad();
                        e.setId("Entidad "+(i%5));
                                
			t.setEntidadId(e);
			int mod=(i%25);
			t.setCliente("Nombre Cliente "+mod);
			t.setNumero_cuenta("445612378912340"+(mod+100));
			t.setTipo_tarjeta((i%3)!=0?"Debito":"Credito");*/
			
			list.add(t);		
		}
		
		return list;	
		
	}
	
	public List<Tarjeta> getTarjetas(){
		return list;
	};
	
	

}
