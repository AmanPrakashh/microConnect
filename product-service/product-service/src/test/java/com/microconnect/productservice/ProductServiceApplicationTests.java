package com.microconnect.productservice;

import com.microconnect.productservice.dto.ProductRequest;
import com.microconnect.productservice.dto.ProductResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
class ProductServiceApplicationTests {


	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");

	@Autowired
	MockMvc mockMvc;

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry properties)
	{
		properties.add("spring.data.mongodb.uri", mongoDBContainer:: getReplicaSetUrl);
	}
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest =getProductRequest();
	mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(productRequest)))
			.andExpect(status().isCreated());

	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("i Phone 15")
				.price(BigDecimal.valueOf(12.55))
				.description("don't buy if you want to save money, buy if you want to save your privacy")
				.build();
	}


}
