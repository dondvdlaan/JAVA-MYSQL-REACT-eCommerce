package com.manyroadsdev.server;

import com.manyroadsdev.server.model.*;
import com.manyroadsdev.server.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerMain {

	public static void main(String[] args) {
		SpringApplication.run(ServerMain.class, args);
	}

	@Bean
	public CommandLineRunner demo(CountryRepository countryRepository) {
		return (args) -> {

			Currency euro = new Currency("Euro","€");

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

		};

	}
}