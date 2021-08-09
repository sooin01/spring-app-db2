package com.my.app.sample.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.my.app.sample.dao.SampleDao;
import com.my.app.sample.entity.Sample;

@Service
@Transactional
public class SampleService {

	private static final Logger logger = Logger.getLogger(SampleService.class);

	@Autowired
	private SampleDao sampleDao;

	public void run() {
		for (Sample sample : sampleDao.findSeqs()) {
			logger.debug(sample.getSeq());
		}
	}

}
