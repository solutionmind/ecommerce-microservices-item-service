package com.techrocking.item.data.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.techrocking.item.entity.Item;
import com.techrocking.item.payload.GetItemResponse;

@Component
public class ItemDataConverter {
	
	public GetItemResponse convert(List<Item> itemsEntities) {
		
		GetItemResponse response = new GetItemResponse();
		List<com.techrocking.item.payload.Item> items = new ArrayList<>();
		
		itemsEntities.forEach(itemEntity -> {
			com.techrocking.item.payload.Item item = new com.techrocking.item.payload.Item();
			item.setName(itemEntity.getName());
			item.setCategory(itemEntity.getCategory());
			item.setSubCategory(itemEntity.getSubCategory());
			item.setQuantity(itemEntity.getQuantity());
			items.add(item);
		});
		
		response.setItems(items);
		return response;
		
	}

}
