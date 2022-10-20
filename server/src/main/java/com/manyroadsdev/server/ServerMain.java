package com.manyroadsdev.server;

import com.manyroadsdev.server.model.*;
import com.manyroadsdev.server.repository.CountryRepository;
import com.manyroadsdev.server.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Main class for this eCommerce application. The commandLineRunner is used to
 * fill the database and to be able start a demo.
 */
@SpringBootApplication
public class ServerMain {

	public static void main(String[] args) {
		SpringApplication.run(ServerMain.class, args);
	}

	@Bean
	public CommandLineRunner demo(CountryRepository countryRepository, ProductRepository productRepository) {
		return (args) -> {
			// Euro currency
			Currency euro = new Currency("Euro","€");

			// 3 European cities to ship to
			Country nl = new Country("NL","Nederland");
			Country sp = new Country("SP","Spain");
			Country g = new Country("G","Germany");

			Province nh = new Province("NH","Noord-Holland");
			Price sameDayPrice = new Price(euro.getSymbol(),10,euro.getCode());
			ShippingOption sameDay = new ShippingOption("Same Day Delivery",sameDayPrice);
			nh.getOptions().add(sameDay);

			Province zh = new Province("ZH","Zuid-Holland");
			Province b = new Province("B","Brabant");

			nl.getProvinces().add(nh);
			nl.getProvinces().add(zh);
			nl.getProvinces().add(b);
			countryRepository.save(nl);

			Province rp = new Province("RP","Rheinland-Pfalz");
			Province bw = new Province("BW","Baden-Wurtenberg");
			Province h = new Province("H","Hessen");

			g.getProvinces().add(rp);
			g.getProvinces().add(bw);
			g.getProvinces().add(h);
			countryRepository.save(g);

			Province m = new Province("M","Madrid");
			Province gu = new Province("BG","Guadalajara");
			Province a = new Province("A","Andalucia");

			sp.getProvinces().add(m);
			sp.getProvinces().add(gu);
			sp.getProvinces().add(a);
			countryRepository.save(sp);

			// 3 Test products
			Product table = new Product();
			table.setPrice(new Price(euro.getSymbol(),105.50,euro.getCode()));
			table.setActive(1);
			table.setProdDescription("Beatiful wooden table");
			table.setProdImage("https://www.moebelliebling.de/thumbnail/8e/be/c2/1653465188/-bde79b498c8b1557a8b82791aedf3e22_1920x1920.jpg");
			table.setProdName("Wooden table");
			productRepository.save(table);

			Product chair = new Product();
			chair.setPrice(new Price(euro.getSymbol(),145.50,euro.getCode()));
			chair.setActive(1);
			chair.setProdDescription("Stylish office chair");
			chair.setProdImage("https://cdn.apartmenttherapy.info/image/upload/f_auto,q_auto:eco,c_fit,w_730,h_730/gen-workflow%2Fproduct-database%2FDomica_Conference_Chair-wayfair");
			chair.setProdName("Office chair");
			productRepository.save(chair);

			Product lamp = new Product();
			lamp.setPrice(new Price(euro.getSymbol(),75.90,euro.getCode()));
			lamp.setActive(1);
			lamp.setProdDescription("Modern studio lamp");
			lamp.setProdImage("https://cdn02.plentymarkets.com/9krpwzxa6mmo/item/images/81061852/full/92065-2.jpg");
			lamp.setProdName("Studio lamp");
			productRepository.save(lamp);

			Product couch = new Product();
			couch.setPrice(new Price(euro.getSymbol(),575.85,euro.getCode()));
			couch.setActive(1);
			couch.setProdDescription("Lazy living room couch");
			couch.setProdImage("https://www.lebensart-berlin.de/wp-content/uploads/2020/10/design-sofa-lazy2-berlin-steglitz-3.1618925304.jpg");
			couch.setProdName("Living room couch");
			productRepository.save(couch);
		};

	}
}