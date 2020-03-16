package com.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.main.doa.Repo;
import com.main.model.ModelObject;

@RestController
public class Service {
	@Autowired
	private Repo repo;
	
	private List modelObjects = new ArrayList<ModelObject>();
	
	public Service () {
		this.modelObjects.add(new ModelObject(0, "Nice Object"));
	}
	
	@RequestMapping(value = "/getModelObjects", method = RequestMethod.GET)
	public List<ModelObject> listModelObjects () {
		List l = repo.getAll();
		return l;
	}
	
	@RequestMapping(value = "/addModelObject", method = RequestMethod.POST)
	public Integer addNewModelObject (@RequestBody ModelObject modelObject) {
		modelObject.setId(this.modelObjects.size());
		this.modelObjects.add(modelObject);
		return modelObject.getId();
	}
}
