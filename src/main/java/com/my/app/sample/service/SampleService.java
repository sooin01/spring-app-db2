package com.my.app.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.app.sample.dao.SampleDao;
import com.my.app.sample.entity.Sample;

@Service
@Transactional
public class SampleService {

	@Autowired
	private SampleDao sampleDao;

	public void run() {
		for (Sample sample : sampleDao.findSeqs()) {
			System.out.println(sample.getSeq());
		}
	}

}
