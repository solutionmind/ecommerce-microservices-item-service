package com.techrocking.item.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techrocking.item.data.converter.ItemDataConverter;
import com.techrocking.item.payload.GetItemResponse;
import com.techrocking.item.payload.SaveItemRequest;
import com.techrocking.item.payload.SaveItemResponse;
import com.techrocking.item.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService service;
	
	@Autowired
	private ItemDataConverter converter;
	
	@Value("${item.service.message}")
	private String message;
	
	@GetMapping
	public GetItemResponse getItem() {
		List<com.techrocking.item.entity.Item> itemEntityList =  service.getItem();
		return converter.convert(itemEntityList);
	}
	
	@GetMapping("{id}")
	public GetItemResponse getItem(@PathVariable(value = "id") Long itemId) {
		com.techrocking.item.entity.Item item =  service.getItem(itemId);
		return converter.convert(Arrays.asList(item));
	}
	
	@PostMapping()
	public SaveItemResponse saveItem(@RequestBody SaveItemRequest payload) {
		com.techrocking.item.entity.Item item =  service.saveItem(payload);
		
		SaveItemResponse response = new SaveItemResponse();
		response.setMessage("item saved successfully");
		response.setId(item.getId());
		return response;
	}
	
	@GetMapping("ping")
	public String ping() {
		return message;
	}

}
