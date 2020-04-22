package it.polito.tdp.meteo.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model m = new Model();
		
		System.out.println(m.getUmiditaMedia("12")+m.getCostoMin());
		
		System.out.println(m.trovaSequenza("05")+m.getCostoMin());
		
		System.out.println(m.trovaSequenza("01")+m.getCostoMin());
		
		System.out.println(m.trovaSequenza("06")+m.getCostoMin());
	}

}
