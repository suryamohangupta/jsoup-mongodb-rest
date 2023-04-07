package com.example.mdbspringboot;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.mdbspringboot.DTO.*;
import com.example.mdbspringboot.DTO.feature.FeatureDto;
import com.example.mdbspringboot.DTO.feature.FeatureName;
import com.example.mdbspringboot.DTO.images.CarImageDto;
import com.example.mdbspringboot.DTO.images.CarImagesDto;
import com.example.mdbspringboot.DTO.specification.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.mdbspringboot.model.GroceryItem;
import com.example.mdbspringboot.repository.CustomItemRepository;
import com.example.mdbspringboot.repository.ItemRepository;

@SpringBootApplication
@EnableMongoRepositories
public class MdbSpringBootApplication implements CommandLineRunner{
	
	@Autowired
	ItemRepository groceryItemRepo;
	
	@Autowired
	CustomItemRepository customRepo;
	
	List<GroceryItem> itemList = new ArrayList<GroceryItem>();

	public static void main(String[] args) {
		SpringApplication.run(MdbSpringBootApplication.class, args);
	}
	
	public void run(String... args) throws IOException {
		List<String> carURLList = new ArrayList<>();
		for(int pageCount = 1; pageCount <= 134; pageCount++)
		{
			String blogUrl = "https://www.cartrade.com/buy-used-cars/p-%d/#so=-1&sc=-1";
			String formattedURL = String.format(blogUrl, pageCount);
			String host = "https://www.cartrade.com";
			Document doc = Jsoup.connect(formattedURL).get();
			Elements elements2 = doc.getElementsByClass("searchFilters-cls");
			for (Element element : elements2) {
				for (Element childElement : element.children())
				{
					if(childElement.is("li"))
					{
						for (Element childElement2 : childElement.getElementsByClass("grid_cnt_new  card-detail-block"))
						{
							for (Element childElement3 : childElement2.getElementsByClass("h2heading truncate"))
							{
								System.out.println(host + childElement3.select("a").attr("href"));
								carURLList.add(host + childElement3.select("a").attr("href"));
							}
						}
						for (Element childElement2 : childElement.getElementsByClass("grid_cnt_new card-detail-block__absure card-detail-block"))
						{
							for (Element childElement3 : childElement2.getElementsByClass("h2heading truncate"))
							{
								System.out.println(host + childElement3.select("a").attr("href"));
								carURLList.add(host + childElement3.select("a").attr("href"));
							}
						}
					}
				}
			}
			pageCount++;
			System.out.println("page no :" + pageCount + "  carURList" + carURLList.size());
		}
		System.out.println("carURList" + carURLList.size());

		List<CarDto> carDtoList = new ArrayList<>();
		for (String carURL : carURLList)
		{
			CarDto carDto = getCarDtoForURL(carURL);
			carDtoList.add(carDto);
		}
		System.out.println(carDtoList.size());

//			// Clean up any previous data
//			groceryItemRepo.deleteAll(); // Doesn't delete the collection
//
//			System.out.println("-------------CREATE GROCERY ITEMS-------------------------------\n");
//
//			createGroceryItems();
//
//			System.out.println("\n----------------SHOW ALL GROCERY ITEMS---------------------------\n");
//
//			showAllGroceryItems();
//
//			System.out.println("\n--------------GET ITEM BY NAME-----------------------------------\n");
//
//			getGroceryItemByName("Whole Wheat Biscuit");
//
//			System.out.println("\n-----------GET ITEMS BY CATEGORY---------------------------------\n");
//
//			getItemsByCategory("millets");
//
//			System.out.println("\n-----------UPDATE CATEGORY NAME OF ALL GROCERY ITEMS----------------\n");
//
//			updateCategoryName("snacks");
//
//			System.out.println("\n-----------UPDATE QUANTITY OF A GROCERY ITEM------------------------\n");
//
//			updateItemQuantity("Bonny Cheese Crackers Plain", 10);
//
//			System.out.println("\n----------DELETE A GROCERY ITEM----------------------------------\n");
//
//			deleteGroceryItem("Kodo Millet");
//
//			System.out.println("\n------------FINAL COUNT OF GROCERY ITEMS-------------------------\n");
//
//			findCountOfGroceryItems();
//
//			System.out.println("\n-------------------THANK YOU---------------------------");
	}

	private CarDto getCarDtoForURL(String url) throws IOException {
		CarDto carDto = new CarDto();
		try
		{
			Document doc = Jsoup.connect(url).get();

			//Make,Model,Variant
			Elements topBarElements = doc.getElementsByClass("breadcrumb").select("li");
			//City
			String city = topBarElements.get(2).select("a").select("span").text();
			carDto.setCity(city);
			//MAKE
			String make = topBarElements.get(3).select("a").select("span").text();
			carDto.setMake(make);
			//Model
			String model = topBarElements.get(4).select("a").select("span").text();
			carDto.setModel(model);
			//Variant
			if(topBarElements.size() == 6)
			{
				String variantName = topBarElements.get(5).select("span").text();
				carDto.setVariant(variantName);
			}


			//Car Details
			Elements detailSidebar = doc.getElementsByClass("v_details details-page-sidebar");
			//Price
			String priceInNumber = detailSidebar.select("div.pull-left").select("span").attr("content");
			String priceWithCurrency = detailSidebar.select("div.pull-left").select("span").text();
			carDto.setPriceInNumber(priceInNumber);
			carDto.setPriceWithCurrency(priceWithCurrency);

			//EMI-StartedAt-Amount
			String emiStartedAtAmount = detailSidebar.select("div.splLinks-wrap").select("span").select("a").text();
			carDto.setEmiStartedAtAmount(emiStartedAtAmount);


			//Sidebar Details
			CarDetailDTO carDetailDTO = new CarDetailDTO();
			Elements tableElement = detailSidebar.tagName("v_table").select("tr");
			for (int i = 0; i < tableElement.size() - 1; i++) {
				String key = tableElement.get(i).select("td").get(0).text();
				String value = tableElement.get(i).select("td").get(1).text();
				if (key.equals("CITY")) {
					carDetailDTO.setCity(value);
				} else if (key.equals("FUEL TYPE")) {
					carDetailDTO.setFuelType(value);
				} else if (key.equals("KMS Driven")) {
					carDetailDTO.setKMSDriven(value);
				} else if (key.equals("COLOUR")) {
					carDetailDTO.setColour(value);
				} else if (key.equals("NUMBER OF OWNERS")) {
					carDetailDTO.setNumberOfOwners(value);
				} else if (key.equals("INSURANCE TYPE")) {
					carDetailDTO.setInsuranceType(value);
				} else if (key.equals("INSURANCE EXPIRY")) {
					carDetailDTO.setInsuranceExpiry(value);
				} else if (key.equals("MANUFACTURING YEAR")) {
					carDetailDTO.setManufacturingYear(value);
				} else if (key.equals("REGISTRATION TYPE")) {
					carDetailDTO.setRegistrationType(value);
				} else if (key.equals("RTO LOCATION")) {
					carDetailDTO.setRtoLocation(value);
				}
			}
			carDto.setCarDetailDTO(carDetailDTO);

			//Description
			Elements descriptionElement = doc.getElementsByClass("car_dts");
			carDto.setDescription(descriptionElement.select("p").text());

			//Images
			Elements imagesElements = doc.getElementsByClass("widgetBox v_images");
			CarImagesDto carImagesDto = new CarImagesDto();

			CarImageDto carMainImageDto = new CarImageDto();
			carMainImageDto.setImageUrl(imagesElements.select("div.v_mainimage").select("img").attr("src"));
			carMainImageDto.setImageTitle(imagesElements.select("div.v_mainimage").select("img").attr("title"));
			carImagesDto.setMainImageDto(carMainImageDto);

			Elements imagesListElements = doc.getElementsByClass("carousel pull-left carousel_wrap").select("li");
			List<CarImageDto> carImageDtoList = new ArrayList<>();
			for(Element element : imagesListElements)
			{
				CarImageDto carImageDto = new CarImageDto();
				carImageDto.setImageUrl(element.select("img").attr("src"));
				carImageDto.setImageTitle(element.select("img").attr("title"));
				carImageDtoList.add(carImageDto);
			}
			carImagesDto.setImageDtos(carImageDtoList);
			carDto.setCarImagesDto(carImagesDto);


			//Features
			List<FeatureDto> featureDtoList = new ArrayList<>();
			Elements featuresElements = doc.getElementsByClass("feature-details__item");
			for (Element featureElement : featuresElements)
			{
				FeatureDto featureDto = new FeatureDto();

				String featureName = featureElement.select("h2").text();
				featureDto.setFeatureName(FeatureName.getFeatureNameFromString(featureName));

				List<String> featureDetailList = new ArrayList<>();
				Elements featureDetailsElementList = featureElement.getElementsByClass("feature-details__item-element opacity50");
				for (Element featureDetailElement : featureDetailsElementList)
				{
					featureDetailList.add(featureDetailElement.text());
				}
				featureDto.setFeatureDetails(featureDetailList);

				featureDtoList.add(featureDto);
			}
			carDto.setFeatureDtoList(featureDtoList);



			//Specifications
			SpecificationDto specificationDto = new SpecificationDto();
			Elements specificationElements = doc.getElementsByClass("specs-details__item");
			//Engine and Transmission
			Elements engineAndTransmissionElement = specificationElements.get(0).getElementsByClass("car_details_block");
			EngineDto engineDto = new EngineDto();
			TransmissionDto transmissionDto = new TransmissionDto();
			for(Element element: engineAndTransmissionElement)
			{
				String key = element.select("span").get(0).text();
				String value = element.select("span").get(1).text();
				if(key.equals("Engine"))
				{
					engineDto.setEngine(value);
				}
				else if(key.equals("Engine Type"))
				{
					engineDto.setEngineType(value);
				}
				else if(key.equals("Fuel Type"))
				{
					engineDto.setFuelType(value);
				}
				else if(key.equals("Max Power (bhp@rpm)"))
				{
					engineDto.setMaxPower(value);
				}
				else if(key.equals("Max Torque (Nm@rpm)"))
				{
					engineDto.setMaxTorque(value);
				}
				else if(key.equals("Mileage (ARAI)"))
				{
					engineDto.setMileage(value);
				}
				else if(key.equals("Drivetrain"))
				{
					engineDto.setDriveTrain(value);
				}
				else if(key.equals("Transmission"))
				{
					transmissionDto.setTransmission(value);
				}
				else if(key.equals("Emission Standard"))
				{
					transmissionDto.setEmissionStandard(value);
				}
				else if(key.equals("Turbocharger / Supercharger"))
				{
					transmissionDto.setIsTurbochargerOrSuperCharger(value);
				}
			}
			specificationDto.setEngineDto(engineDto);
			specificationDto.setTransmissionDto(transmissionDto);

			//Dimension And Weight
			Elements dimensionAndWeightElement = specificationElements.get(1).getElementsByClass("car_details_block");
			DimensionDto dimensionDto = new DimensionDto();
			WeightDto weightDto = new WeightDto();
			for(Element element: dimensionAndWeightElement)
			{
				String key = element.select("span").get(0).text();
				String value = element.select("span").get(1).text();
				if(key.equals("Length"))
				{
					dimensionDto.setLength(value);
				}
				else if(key.equals("Width"))
				{
					dimensionDto.setWidth(value);
				}
				else if(key.equals("Height"))
				{
					dimensionDto.setHeight(value);
				}
				else if(key.equals("Wheelbase"))
				{
					dimensionDto.setWheelBase(value);
				}
				else if(key.equals("Ground Clearance"))
				{
					dimensionDto.setGroundClearance(value);
				}
				else if(key.equals("Kerb Weight"))
				{
					weightDto.setKerbWeight(value);
				}
			}
			specificationDto.setDimensionDto(dimensionDto);
			specificationDto.setWeightDto(weightDto);

			//Capacity
			Elements capacityElement = specificationElements.get(2).getElementsByClass("car_details_block");
			CapacityDto capacityDto = new CapacityDto();
			for(Element element: capacityElement)
			{
				String key = element.select("span").get(0).text();
				String value = element.select("span").get(1).text();
				if(key.equals("Doors"))
				{
					capacityDto.setDoors(value);
				}
				else if(key.equals("Seating Capacity"))
				{
					capacityDto.setSeatingCapacity(value);
				}
				else if(key.equals("No of Seating Row"))
				{
					capacityDto.setNumberOfSeatingRows(value);
				}
				else if(key.equals("Bootspace"))
				{
					capacityDto.setBootSpace(value);
				}
				else if(key.equals("Fuel Tank Capacity"))
				{
					capacityDto.setFuelTankCapacity(value);
				}
			}
			specificationDto.setCapacityDto(capacityDto);

			//Suspensions, Brakes, Steering & Tyres
			Elements suspensionBrakesSteeringTyresElement = specificationElements.get(3).getElementsByClass("car_details_block");
			SuspensionDto suspensionDto = new SuspensionDto();
			BrackeDto brackeDto = new BrackeDto();
			SteeringDto steeringDto = new SteeringDto();
			TyresDto tyresDto = new TyresDto();
			for(Element element: suspensionBrakesSteeringTyresElement)
			{
				System.out.println(element);
				String key = element.select("span").get(0).text();
				String value = element.select("span").get(1).text();
				if(key.equals("Front Suspension"))
				{
					suspensionDto.setFrontSuspension(value);
				}
				else if(key.equals("Rear Suspension"))
				{
					suspensionDto.setRearSuspension(value);
				}
				else if(key.equals("Front Brake Type"))
				{
					brackeDto.setFrontBrakeType(value);
				}
				else if(key.equals("Rear Brake Type"))
				{
					brackeDto.setRearBrakeType(value);
				}
				else if(key.equals("Minimum Turning Radius"))
				{
					steeringDto.setMinimumTurningRadius(value);
				}
				else if(key.equals("Steering Type"))
				{
					steeringDto.setSteeringType(value);
				}
				else if(key.equals("Wheels"))
				{
					tyresDto.setWheelsType(value);
				}
				else if(key.equals("Spare Wheel"))
				{
					tyresDto.setSpareWheelType(value);
				}
				else if(key.equals("Front Tyres"))
				{
					tyresDto.setFrontTyresDimension(value);
				}
				else if(key.equals("Rear Tyres"))
				{
					tyresDto.setRearTyresDimension(value);
				}
			}
			specificationDto.setSuspensionDto(suspensionDto);
			specificationDto.setBrackeDto(brackeDto);
			specificationDto.setSteeringDto(steeringDto);
			specificationDto.setTyresDto(tyresDto);

			carDto.setSpecificationDto(specificationDto);
			System.out.println(carDto);
		}
		catch(Exception exception)
		{
			System.out.println("Exception for url: "+ url + " trace : "+ exception.getLocalizedMessage());
		}
		return carDto;
	}

	// CRUD operations

	//CREATE
	void createGroceryItems() {
		System.out.println("Data creation started...");

		groceryItemRepo.save(new GroceryItem("Whole Wheat Biscuit", "Whole Wheat Biscuit", 5, "snacks"));
		groceryItemRepo.save(new GroceryItem("Kodo Millet", "XYZ Kodo Millet healthy", 2, "millets"));
		groceryItemRepo.save(new GroceryItem("Dried Red Chilli", "Dried Whole Red Chilli", 2, "spices"));
		groceryItemRepo.save(new GroceryItem("Pearl Millet", "Healthy Pearl Millet", 1, "millets"));
		groceryItemRepo.save(new GroceryItem("Cheese Crackers", "Bonny Cheese Crackers Plain", 6, "snacks"));
		
		System.out.println("Data creation complete...");
	}
	
	// READ
	// 1. Show all the data
	 public void showAllGroceryItems() {
		 
		 itemList = groceryItemRepo.findAll();
		 
		 itemList.forEach(item -> System.out.println(getItemDetails(item)));
	 }
	 
	 // 2. Get item by name
	 public void getGroceryItemByName(String name) {
		 System.out.println("Getting item by name: " + name);
		 GroceryItem item = groceryItemRepo.findItemByName(name);
		 System.out.println(getItemDetails(item));
	 }
	 
	 // 3. Get name and items of a all items of a particular category
	 public void getItemsByCategory(String category) {
		 System.out.println("Getting items for the category " + category);
		 List<GroceryItem> list = groceryItemRepo.findAll(category);
		 
		 list.forEach(item -> System.out.println("Name: " + item.getName() + ", Quantity: " + item.getItemQuantity()));
	 }
	 
	 // 4. Get count of documents in the collection
	 public void findCountOfGroceryItems() {
		 long count = groceryItemRepo.count();
		 System.out.println("Number of documents in the collection = " + count);
	 }
	 
	 // UPDATE APPROACH 1: Using MongoRepository
	 public void updateCategoryName(String category) {
		 
		 // Change to this new value
		 String newCategory = "munchies";
		 
		 // Find all the items with the category 
		 List<GroceryItem> list = groceryItemRepo.findAll(category);
		 
		 list.forEach(item -> {
			 // Update the category in each document
			 item.setCategory(newCategory);
		 });
		 
		 // Save all the items in database
		 List<GroceryItem> itemsUpdated = groceryItemRepo.saveAll(list);
		 
		 if(itemsUpdated != null)
			 System.out.println("Successfully updated " + itemsUpdated.size() + " items.");		 
	 }
	 
	 
	 // UPDATE APPROACH 2: Using MongoTemplate
	 public void updateItemQuantity(String name, float newQuantity) {
		 System.out.println("Updating quantity for " + name);
		 customRepo.updateItemQuantity(name, newQuantity);
	 }
	 
	 // DELETE
	 public void deleteGroceryItem(String id) {
		 groceryItemRepo.deleteById(id);
		 System.out.println("Item with id " + id + " deleted...");
	 }
	 // Print details in readable form
	 
	 public String getItemDetails(GroceryItem item) {

		 System.out.println(
		 "Item Name: " + item.getName() + 
		 ", \nItem Quantity: " + item.getItemQuantity() + 
		 ", \nItem Category: " + item.getCategory()
		 );
		 
		 return "";
	 }
}

