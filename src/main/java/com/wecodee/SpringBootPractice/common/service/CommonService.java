package com.wecodee.SpringBootPractice.common.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wecodee.SpringBootPractice.admin.constant.EnumData;
import com.wecodee.SpringBootPractice.admin.model.FunctionData;
import com.wecodee.SpringBootPractice.admin.repository.FunctionDataRepository;
import com.wecodee.SpringBootPractice.admin.util.Helper;
import com.wecodee.SpringBootPractice.usermanagement.model.Role;
import com.wecodee.SpringBootPractice.usermanagement.model.User;

import net.minidev.json.JSONObject;

@Service
public class CommonService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FunctionDataRepository functionDataRepository;

	public void saveScreenData(Object recordDataObj, String createdBy, String pKey, String screenId, String action,
			int recordVersion, String status) throws JsonProcessingException {
		log.info("In common service saveScreenData method is executing");
		ObjectMapper mapper = new ObjectMapper();
		FunctionData functionData = new FunctionData();
		String recordDataString = mapper.writeValueAsString(recordDataObj);
		functionData.setPkey(pKey);
		functionData.setFunctionId(screenId);
		functionData.setAction(action);
		functionData.setFunctionJsonData(recordDataString);
		if (status.equals(EnumData.INACTIVE.getName())) {
			functionData.setStatus(EnumData.INACTIVE.getName());
		} else if (status.equals(EnumData.DISABLED.getName())) {
			functionData.setStatus(EnumData.DISABLED.getName());
		}
		functionData.setCreatedBy(createdBy);
		functionData.setCreatedDate(Helper.getCurrentDateAndTime());
		functionData.setRecordVersion(recordVersion);
		functionDataRepository.save(functionData);
	}

	public void updateScreenData(Object recordDataObj, String createdBy, String pKey, String functionId, String action,
			int recordVersion, String status) throws JsonProcessingException {
		log.info("In updateScreenData..");
		ObjectMapper mapper = new ObjectMapper();
		FunctionData functionData = functionDataRepository.getByFunctionIdAndPkey(functionId, pKey);
		String recordDataString = mapper.writeValueAsString(functionData);
		functionData.setPkey(pKey);
		functionData.setFunctionId(functionId);
		functionData.setAction(action);
		functionData.setFunctionJsonData(recordDataString);
		if (status.equals(EnumData.INACTIVE.getName())) {
			functionData.setStatus(EnumData.INACTIVE.getName());
		} else if (status.equals(EnumData.DISABLED.getName())) {
			functionData.setStatus(EnumData.DISABLED.getName());
		}
		functionData.setCreatedBy(createdBy);
		functionData.setCreatedDate(Helper.getCurrentDateAndTime());
		functionData.setRecordVersion(recordVersion);
		functionDataRepository.save(functionData);
	}

	public void saveScreenDataForGroup(Object recordDataObj, String createdBy, String pKey, String screenId,
			String action, int recordVersion, String status) throws JsonProcessingException {
		log.info("In common service saveScreenData method is executing");
		ObjectMapper mapper = new ObjectMapper();
		FunctionData functionData = new FunctionData();
		String recordDataString = mapper.writeValueAsString(recordDataObj);
		functionData.setPkey(pKey);
		functionData.setFunctionId(screenId);
		functionData.setAction(action);
		functionData.setFunctionJsonData(recordDataString);
		if (status.equals(EnumData.INACTIVE.getName())) {
			functionData.setStatus(EnumData.INACTIVE.getName());
		} else if (status.equals(EnumData.DISABLED.getName())) {
			functionData.setStatus(EnumData.DISABLED.getName());
		}
		functionData.setCreatedBy(createdBy);
		functionData.setCreatedDate(Helper.getCurrentDateAndTime());
		functionData.setRecordVersion(recordVersion);
		functionDataRepository.save(functionData);
	}

	public User makeUserDisabled(User userfrmDb) {
		log.info("In common service makeUserDisabled method is executing");
		User user = new User();
		user.setApproved(false);
//		user.setCreatedBy(JwtRequestFilter.loggedInUserId);
		user.setCreatedDate(Helper.getCurrentDateAndTime());
		user.setRecordStatus(EnumData.DISABLED.getName());
		user.setUserId(userfrmDb.getUserId());
		user.setPassword(userfrmDb.getPassword());
		user.setFirstName(userfrmDb.getFirstName());
		user.setLastName(userfrmDb.getLastName());
		user.setChgPassFlg(userfrmDb.isChgPassFlg());
		user.setCountryCode(userfrmDb.getCountryCode());
		user.setEmailId(userfrmDb.getEmailId());
		user.setImageData(userfrmDb.getImageData());
		user.setLastUpdatedBy(userfrmDb.getLastUpdatedBy());
		user.setLastUpdatedDate(userfrmDb.getLastUpdatedDate());
		user.setPendingApproval(userfrmDb.getPendingApproval());
		user.setPhoneNumber(userfrmDb.getPhoneNumber());
		user.setRecordVersion(userfrmDb.getRecordVersion());
		user.setUserRoles(userfrmDb.getUserRoles());
		return user;
	}

	public Role makeRoleDisabled(Role rolesfmDb) {
		log.info("In common service makeRoleDisabled method is executing");
		Role roles = new Role();
		roles.setApproved(false);
//		roles.setCreatedBy(JwtRequestFilter.loggedInUserId);
		roles.setCreatedDate(Helper.getCurrentDateAndTime());
		roles.setRecordStatus(EnumData.DISABLED.getName());
		roles.setRoleId(rolesfmDb.getRoleId());
		roles.setAppId(rolesfmDb.getAppId());
		roles.setLastUpdatedBy(rolesfmDb.getLastUpdatedBy());
		roles.setLastUpdatedDate(rolesfmDb.getLastUpdatedDate());
		roles.setPendingApproval(rolesfmDb.getPendingApproval());
		roles.setRecordVersion(rolesfmDb.getRecordVersion());
		roles.setRoleDescription(rolesfmDb.getRoleDescription());
		roles.setRoleFunctions(rolesfmDb.getRoleFunctions());
		return roles;
	}

	// ATM
//		public AtmDetails makeAtmDeatailsDisable(AtmDetails atmDetailsDb) {
//			log.info("In common service makeAtmDeatailsDisable method is executing");
//			AtmDetails atmDetails = new AtmDetails();
//			atmDetails.setApproved(false);
//			atmDetails.setCreatedBy(JwtRequestFilter.loggedInUserId);
//			atmDetails.setCreatedDate(Helper.getCurrentDateAndTime());
//			atmDetails.setRecordStatus(EnumData.DISABLED.getName());
//			atmDetails.setAtmName(atmDetailsDb.getAtmName());
//			atmDetails.setLastUpdatedBy(atmDetailsDb.getLastUpdatedBy());
//			atmDetails.setLastUpdatedDate(atmDetailsDb.getLastUpdatedDate());
//			atmDetails.setPendingApproval(atmDetailsDb.getPendingApproval());
//			atmDetails.setRecordVersion(atmDetailsDb.getRecordVersion());
//			return atmDetails;
//		}

	// BRANCH
//		public BranchDetails makeBranchDetailsDisable(BranchDetails branchDetailsDb) {
//			log.info("In common service makeBranchDetailsDisable method is executing");
//			BranchDetails branchDetails = new BranchDetails();
//			branchDetails.setApproved(false);
//			branchDetails.setCreatedBy(JwtRequestFilter.loggedInUserId);
//			branchDetails.setCreatedDate(Helper.getCurrentDateAndTime());
//			branchDetails.setRecordStatus(EnumData.DISABLED.getName());
//			branchDetails.setBranchName(branchDetailsDb.getBranchName());
//			branchDetails.setLastUpdatedBy(branchDetailsDb.getLastUpdatedBy());
//			branchDetails.setLastUpdatedDate(branchDetailsDb.getLastUpdatedDate());
//			branchDetails.setPendingApproval(branchDetailsDb.getPendingApproval());
//			branchDetails.setRecordVersion(branchDetailsDb.getRecordVersion());
//			return branchDetails;
//		}

	public <T> JSONObject getPaginationData(int pageNumber, int pageSize, Long count, List<T> list) {
		Page<T> pageData = new PageImpl<T>(list, PageRequest.of(pageNumber, pageSize), count);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("currentPage", pageData.getNumber());
		jsonObject.put("totalItems", pageData.getTotalElements());
		jsonObject.put("totalPages", pageData.getTotalPages());
		jsonObject.put("items", pageData.getContent());
		return jsonObject;
	}

	public <T> String convertFromXmlToString(T object) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(this.getClass());
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(object, sw);
			String xmlContent = sw.toString();
			log.info(xmlContent);
			return xmlContent;
		} catch (Exception e) {
			log.info("In exception ->" + e);
			e.printStackTrace();
			return null;
		}

	}

	public Object stringXmlToObject(String xml, Object object) {
		StringReader sr = new StringReader(xml);
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(object.getClass());
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			object = jaxbUnmarshaller.unmarshal(sr);
			return object;
		} catch (JAXBException e) {
			log.error("In exception ->" + e);
			e.printStackTrace();
			return null;
		}
	}
	
	public ByteArrayInputStream generatePdf(String xslPath, String xmlData) {
		try {
			Resource resource = new ClassPathResource(xslPath);
			File file = resource.getFile();
			String path = file.getPath();
			FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
			// Setup a buffer to obtain the content length
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			// Setup FOP
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(path));
			// Make sure the XSL transformation's result is piped through to FOP
			SAXResult res = new SAXResult(fop.getDefaultHandler());
			// Setup input
			Source src = new StreamSource(new StringReader(xmlData));
			// Start the transformation and rendering process
			transformer.transform(src, res);
			// Send content to Browser
			return new ByteArrayInputStream(out.toByteArray());
//	        response.getOutputStream().flush();
		} catch (Exception e) {
			log.error("In exception ->" + e);
			e.printStackTrace();
			return null;
		}
	}

}
