package com.guanghua.edms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guanghua.edms.dao.SpecDao;
import com.guanghua.edms.domain.Spec;
@Service
@Transactional
public class SpecServiceImpl implements SpecService {
	@Autowired  
	private SpecDao specDao;
	@Override
	public int addSpec(Spec spec) {
		return specDao.addSpec(spec);
	}

	@Override
	public int editSpec(Spec spec) {
		return specDao.editSpec(spec);
	}

	@Override
	public int removeSpecs(List<Spec> specs) {
		return specDao.removeSpecs(specs);
	}

}
