package com.codevergence.neolink.admin;

import com.codevergence.neolink.admin.model.Branch;
import com.codevergence.neolink.admin.model.BranchClass;
import com.codevergence.neolink.admin.model.BranchType;
import com.codevergence.neolink.admin.model.EmailScheduler;
import com.codevergence.neolink.admin.model.EmailTemplate;
import com.codevergence.neolink.admin.model.ExcelReport;
import com.codevergence.neolink.admin.model.InvestStandardBI;
import com.codevergence.neolink.admin.model.KabupatenKota;
import com.codevergence.neolink.admin.model.LineOfBusiness;
import com.codevergence.neolink.admin.model.Parameter;
import com.codevergence.neolink.admin.model.PerijinanApproval;
import com.codevergence.neolink.admin.model.PerijinanBI;
import com.codevergence.neolink.admin.model.PerijinanUmum;
import com.codevergence.neolink.admin.model.Province;
import com.codevergence.neolink.admin.model.Region;
import com.codevergence.neolink.admin.model.RegionMapping;
import com.codevergence.neolink.admin.model.ZonaBI;
import com.codevergence.neolink.admin.service.BranchClassServiceUtil;
import com.codevergence.neolink.admin.service.BranchServiceUtil;
import com.codevergence.neolink.admin.service.BranchTypeServiceUtil;
import com.codevergence.neolink.admin.service.EmailSchedulerServiceUtil;
import com.codevergence.neolink.admin.service.EmailTemplateServiceUtil;
import com.codevergence.neolink.admin.service.ExcelReportServiceUtil;
import com.codevergence.neolink.admin.service.InvestStandardBIServiceUtil;
import com.codevergence.neolink.admin.service.KabupatenKotaServiceUtil;
import com.codevergence.neolink.admin.service.LineOfBusinessServiceUtil;
import com.codevergence.neolink.admin.service.ParameterServiceUtil;
import com.codevergence.neolink.admin.service.PerijinanApprovalServiceUtil;
import com.codevergence.neolink.admin.service.PerijinanBIServiceUtil;
import com.codevergence.neolink.admin.service.PerijinanUmumServiceUtil;
import com.codevergence.neolink.admin.service.ProvinceServiceUtil;
import com.codevergence.neolink.admin.service.RegionMappingServiceUtil;
import com.codevergence.neolink.admin.service.RegionServiceUtil;
import com.codevergence.neolink.admin.service.ZonaBIServiceUtil;
import com.codevergence.neolink.admin.utils.ActionKeys;
import com.codevergence.neolink.admin.utils.Constants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.NoSuchFileEntryException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.model.DLFolder;
import com.liferay.portlet.documentlibrary.service.DLFileEntryLocalServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFileEntryServiceUtil;
import com.liferay.portlet.documentlibrary.service.DLFolderLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.dao.DataIntegrityViolationException;

/**
 * Portlet implementation class NeoLinkAdministrationPortlet
 */
public class NeoLinkAdministrationPortlet extends MVCPortlet {
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse) 
			throws IOException, PortletException {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		PermissionChecker permissionChecker = themeDisplay.getPermissionChecker();
		PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();
		
		StringBuilder tabs = new StringBuilder("zona-bi,lob,branch-type,branch-class,invest-standard-bi,region,region-mapping,provinsi,kabupaten,perijinan,email-scheduler,email-template,excel-report");
		if(permissionChecker.hasPermission(themeDisplay.getScopeGroupId(), portletDisplay.getRootPortletId(), portletDisplay.getResourcePK(), ActionKeys.APPROVER_PERIJINAN)){
			tabs.append(",upload-approval");
		}
		if(permissionChecker.hasPermission(themeDisplay.getScopeGroupId(), portletDisplay.getRootPortletId(), portletDisplay.getResourcePK(), ActionKeys.OPERATOR_PERIJINAN)){
			tabs.append(",upload-revise");
		}
		tabs.append(",parameter");
		
		renderRequest.setAttribute("tabs", tabs.toString());
		
		String tab = ParamUtil.getString(renderRequest, "tabs1", Constants.TAB_ZONA_BI);
		String cmd = ParamUtil.getString(renderRequest, "cmd");
		
		if (Constants.TAB_PARAMETER.equals(tab)) {
			if (Constants.EDIT.equals(cmd)) {
				String param = ParamUtil.getString(renderRequest, "param");
				Parameter parameter = new Parameter();
				if ((param != null) && !"".equals(param)) {
					String value = ParameterServiceUtil.getService(renderRequest).getValue(param);
					parameter.setParam(param);
					parameter.setValue(value);
				}
				renderRequest.setAttribute("parameter", parameter);

			} else {
				List<Parameter> parameters = ParameterServiceUtil.getService(renderRequest).getParameters();
				renderRequest.setAttribute("parameters", parameters);
			}

		} else if (Constants.TAB_ZONA_BI.equals(tab)) {
			if (Constants.EDIT.equals(cmd)) {
				long id = ParamUtil.getLong(renderRequest, "id");
				ZonaBI zonaBi = new ZonaBI();
				if (id != 0) {
					zonaBi = ZonaBIServiceUtil.getService(renderRequest).getById(id);
				}
				renderRequest.setAttribute("zonaBi", zonaBi);
			} else {
				List<ZonaBI> zonabis = ZonaBIServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("zonaBis", zonabis);
			}
		} else if (Constants.TAB_AMI_FORMULA.equals(tab)) {
			
		} else if (Constants.TAB_LOB.equals(tab)) {
			if (Constants.EDIT.equals(cmd)) {
				long id = ParamUtil.getLong(renderRequest, "id");
				LineOfBusiness lineOfBusiness = new LineOfBusiness();
				//List<Region> regions = new ArrayList<Region>();
				//List<Region> masterRegions = RegionServiceUtil.getService(renderRequest).getAll();
				
				if (id != 0) {
					lineOfBusiness = LineOfBusinessServiceUtil.getService(renderRequest).getById(id);
					//get regions
					//regions = RegionServiceUtil.getService(renderRequest).getForUnitBusiness(id);
					//renderRequest.setAttribute("regions", regions);
					//renderRequest.setAttribute("masterRegions", masterRegions);
					
				}
				
				renderRequest.setAttribute("lob", lineOfBusiness);
			} else {
				List<LineOfBusiness> lineOfBusiness = LineOfBusinessServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("lobs", lineOfBusiness);
			}
		}else if (Constants.TAB_EMAIL_TEMPLATE.equals(tab)) {
			if (Constants.EDIT.equals(cmd)) {
				long id = ParamUtil.getLong(renderRequest, "id");
				EmailTemplate emailTemplate = new EmailTemplate();
				if (id != 0) {
					emailTemplate = EmailTemplateServiceUtil.getService(renderRequest).getById(id);
				}
				renderRequest.setAttribute("emailTemplate", emailTemplate);
			}else{
				List<EmailTemplate> emailTemplates = EmailTemplateServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("emailTemplates", emailTemplates);
			}
		} else if (Constants.TAB_BRANCH_TYPE.equals(tab)) {
			if (Constants.EDIT.equals(cmd)) {
				long id = ParamUtil.getLong(renderRequest, "id");
				BranchType branchType = new BranchType();
				if (id != 0) {
					branchType = BranchTypeServiceUtil.getService(renderRequest).withId(id);
				}
				renderRequest.setAttribute("branchType", branchType);
			}else{
				List<BranchType> result = BranchTypeServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("branchTypes", result);
			}
		
		} else if (Constants.TAB_BRANCH_CLASS.equals(tab)) {
			if (Constants.EDIT.equals(cmd)) {
				int id = ParamUtil.getInteger(renderRequest, "id");
				BranchClass branchClass = new BranchClass();
				if (id != 0) {
					branchClass = BranchClassServiceUtil.getService(renderRequest).getBranchClassById(id);
				}
				renderRequest.setAttribute("branchClass", branchClass);
			} else {
				List<BranchClass> branchClasses = BranchClassServiceUtil.getService(renderRequest).getAll(); 
				renderRequest.setAttribute("branchClasses", branchClasses);
			}

		} else if (Constants.TAB_INVEST_STANDARD_BI.equals(tab)) {
			
			Hashtable<Long, BranchType> branchTypes = BranchTypeServiceUtil.getService(renderRequest).getAllAsHash(); 
			if (Constants.EDIT.equals(cmd)) {
				long id = ParamUtil.getLong(renderRequest, "id");
				InvestStandardBI investStandardBI = new InvestStandardBI();
				if (id != 0) {
					investStandardBI = InvestStandardBIServiceUtil.getService(renderRequest).withId(id);
					investStandardBI.setBranchType(branchTypes.get(investStandardBI.getBranchTypeId()));
				}
				renderRequest.setAttribute("investStandardBI", investStandardBI);
			} else {
				List<InvestStandardBI> result = InvestStandardBIServiceUtil.getService(renderRequest).getAll();
				for (int i = 0; i < result.size(); i++) {
					InvestStandardBI isbi = result.get(i);
					//BranchType bloop = branchTypes.get(new Long(isbi.getBranchTypeId()));
					result.get(i).setBranchType(branchTypes.get(new Long(isbi.getBranchTypeId())));
				}
				renderRequest.setAttribute("investStandardBIs", result);
			}
			renderRequest.setAttribute("branchTypes", 
					BranchTypeServiceUtil.getService(renderRequest).getBranchTypeWithPerhitunganAmi());
		}else if (Constants.TAB_PROVINCE.equals(tab)) {
			if (Constants.EDIT.equals(cmd)) {
				long id = ParamUtil.getLong(renderRequest, "id");
				Province province = new Province();
				if (id != 0) {
					province = ProvinceServiceUtil.getService(renderRequest).withId(id);
				}
				renderRequest.setAttribute("province", province);

				// kirim informasi region
				List<Region> regions = RegionServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("regions", regions);

				// kirim informasi zona bi
				List<ZonaBI> zonabis = ZonaBIServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("zonaBis", zonabis);
			}else{
				List<Province> result = ProvinceServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("provinces", result);
			}
		}else if (Constants.TAB_EMAIL_SCHEDULER.equals(tab)) {
			if (Constants.EDIT.equals(cmd)) {
				long id = ParamUtil.getLong(renderRequest, "id");
				EmailScheduler emailScheduler = new EmailScheduler();
				if (id != 0) {
					emailScheduler = EmailSchedulerServiceUtil.getService(renderRequest).withId(id);
				}
				List<EmailTemplate> emailTemplates = EmailTemplateServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("emailTemplates", emailTemplates);
				renderRequest.setAttribute("emailScheduler", emailScheduler);
			}else{
				List<EmailScheduler> result = EmailSchedulerServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("emailSchedulers", result);
			}
			
		}else if (Constants.TAB_REGION.equals(tab)) {
			if (Constants.EDIT.equals(cmd)) {
				long id = ParamUtil.getLong(renderRequest, "id");
				Region region = new Region();
				if (id != 0) {
					region = RegionServiceUtil.getService(renderRequest).withId(id);
				}
				renderRequest.setAttribute("region", region);
			} else {
				List<Region> result = RegionServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("regions", result);
			}
			
		}else if (Constants.TAB_PERIJINAN.equals(tab)) {
			String tabPerijinan = ParamUtil.getString(renderRequest, "tabs2", Constants.TAB_PERIJINAN_UMUM);
			
			if(Constants.TAB_PERIJINAN_UMUM.equals(tabPerijinan)){
				if (Constants.EDIT.equals(cmd)) {
					long id = ParamUtil.getLong(renderRequest, "id");
					PerijinanUmum perijinanUmum = new PerijinanUmum();
					if (id != 0) {
						perijinanUmum = PerijinanUmumServiceUtil.getService(renderRequest).getById(id);
					}
					renderRequest.setAttribute("perijinanUmum", perijinanUmum);
				}else{
					boolean isActive = ParamUtil.getBoolean(renderRequest, "show-active", true);
					boolean isCompleteInformation = ParamUtil.getBoolean(renderRequest, "show-complete-active", true);
					String name = ParamUtil.getString(renderRequest, "name-document");
					String branchName = ParamUtil.getString(renderRequest, "branch");
					
					renderRequest.setAttribute("isActive", isActive);
					renderRequest.setAttribute("isCompleteInformation", isCompleteInformation);
					renderRequest.setAttribute("name", name);
					renderRequest.setAttribute("branchName", branchName);
					
					if (name.isEmpty() || name.equals("Select")) 
						name = null;
					
					if (branchName.isEmpty() || branchName.equals("Select")) 
						branchName = null;
					
					List<Branch> branchs = PerijinanUmumServiceUtil.getService(renderRequest)
							.getBranchHasDocument();
					
					List<String> names = PerijinanUmumServiceUtil.getService(renderRequest)
							.getNameHasDocument();
					
					List<PerijinanUmum> result = PerijinanUmumServiceUtil.getService(renderRequest)
							.getAll(isActive, isCompleteInformation, name, branchName);
					renderRequest.setAttribute("perijinanumums", result);
					renderRequest.setAttribute("branchs", branchs);
					renderRequest.setAttribute("names", names);
				}
			}else if(Constants.TAB_PERIJINAN_BI.equals(tabPerijinan)){
				if (Constants.EDIT.equals(cmd)) {
					long id = ParamUtil.getLong(renderRequest, "id");
					PerijinanBI perijinanBI = new PerijinanBI();
					if (id != 0) {
						perijinanBI = PerijinanBIServiceUtil.getService(renderRequest).getById(id);
					}
					renderRequest.setAttribute("perijinanBI", perijinanBI);
				}else{
					
					//filter here
					boolean isActive = ParamUtil.getBoolean(renderRequest, "show-active", true);
					boolean isCompleteInformation = ParamUtil.getBoolean(renderRequest, "show-complete-active", true);
					String name = ParamUtil.getString(renderRequest, "name-document");
					String branchName = ParamUtil.getString(renderRequest, "branch");
					
					renderRequest.setAttribute("isActive", isActive);
					renderRequest.setAttribute("isCompleteInformation", isCompleteInformation);
					renderRequest.setAttribute("name", name);
					renderRequest.setAttribute("branchName", branchName);
					
					if (name.isEmpty() || name.equals("Select")) 
						name = null;
					
					if (branchName.isEmpty() || branchName.equals("Select")) 
						branchName = null;
					
					List<Branch> branchs = PerijinanBIServiceUtil.getService(renderRequest)
							.getBranchHasDocument();
					
					List<String> names = PerijinanBIServiceUtil.getService(renderRequest)
							.getNameHasDocument();
					
					List<PerijinanBI> result = PerijinanBIServiceUtil.getService(renderRequest)
							.getAll(isActive, isCompleteInformation, name, branchName);
					renderRequest.setAttribute("perijinanbis", result);
					renderRequest.setAttribute("branchs", branchs);
					renderRequest.setAttribute("names", names);
				}
			}
			
		}else if (Constants.TAB_KABUPATEN.equals(tab)) {
			if (Constants.EDIT.equals(cmd)) {
				int id = ParamUtil.getInteger(renderRequest, "id");
				KabupatenKota kabupatenKota = new KabupatenKota();
				if (id != 0) {
					kabupatenKota = KabupatenKotaServiceUtil.getService(renderRequest).getById(id);
				}
				renderRequest.setAttribute("kabupaten", kabupatenKota);
				
				List<Province> result = ProvinceServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("provinces", result);
			} else {
				List<KabupatenKota> result = KabupatenKotaServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("kabupatens", result);
			}

		}else if (Constants.TAB_EXCEL_REPORT.equals(tab)) {
				List<ExcelReport> result = ExcelReportServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("excelreports", result);

		} else if (Constants.TAB_UPLOAD_APPROVAL.equals(tab)) {
			String strParam = ParamUtil.getString(renderRequest, "param");

			ServiceContext serviceContext = new ServiceContext();
			if ((strParam == null) || strParam.equals("")) {
				// do nothing

			} else if (strParam.equals("approve")) {
				String[] fileIds = ParamUtil.getParameterValues(renderRequest, "fileId");
				long newFolderId = getParameterValue(renderRequest, "com.codevergence.btpn.neolink.approved.folderid");
				for (int i = 0; i < fileIds.length; i++) {
					String[] fileInfos = fileIds[i].split("\\|");
//					ServiceContext serviceContext = new ServiceContext();
					try {
					DLFileEntryServiceUtil.moveFileEntry(
							Long.parseLong(fileInfos[0]), 
							Long.parseLong(fileInfos[1]),
							newFolderId, 
							fileInfos[2], 
							serviceContext);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} else if (strParam.equals("revise")) {
				String[] fileIds = ParamUtil.getParameterValues(renderRequest, "fileId");
				long newFolderId = getParameterValue(renderRequest, "com.codevergence.btpn.neolink.revise.folderid");
				for (int i = 0; i < fileIds.length; i++) {
					String[] fileInfos = fileIds[i].split("\\|");
					String comment = ParamUtil.getString(renderRequest, "comment"+fileInfos[3]);
					_log.info(fileInfos[2] + " : " + comment);
					try {
						DLFileEntry fileEntry = DLFileEntryServiceUtil.moveFileEntry(
							Long.parseLong(fileInfos[0]), 
							Long.parseLong(fileInfos[1]),
							newFolderId, 
							fileInfos[2], 
							serviceContext);
						
						PerijinanApproval perijinanApproval = new PerijinanApproval();
						perijinanApproval.setFileId(fileEntry.getFileEntryId());
						perijinanApproval.setFolderId(fileEntry.getFolderId());
						perijinanApproval.setGroupId(fileEntry.getGroupId());
						perijinanApproval.setFileName(fileEntry.getName());
						perijinanApproval.setTitle(fileEntry.getTitle());
						perijinanApproval.setNotes(comment);
						perijinanApproval.setUserId(fileEntry.getUserId());
						perijinanApproval.setApprovalUserIdl(themeDisplay.getUserId());
						PerijinanApprovalServiceUtil.getService(renderRequest).save(perijinanApproval);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

			// pindahkan terlebih dahulu dokumen2 yang ada di folder 'upload'
			long uploadFolderId = getParameterValue(renderRequest, "com.codevergence.btpn.neolink.upload.folderid");
			long approvalFolderId = getParameterValue(renderRequest, "com.codevergence.btpn.neolink.approval.folderid");
			List<DLFileEntry> uploadDLFileEntries = getFileListFromFolderId(uploadFolderId);
			for (DLFileEntry dlFileEntry : uploadDLFileEntries) {
				try {
					DLFileEntryServiceUtil.moveFileEntry(
							dlFileEntry.getGroupId(), // group id
							uploadFolderId, // folder id
							approvalFolderId, // new folder id
							dlFileEntry.getName(), // file name
							serviceContext);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			// baca daftar dokumen yang ada di folder 'approval'
			List<DLFileEntry> approvalDLFileEntries = getFileListFromFolderId(approvalFolderId);
			renderRequest.setAttribute("fileApprovalList", approvalDLFileEntries);
			renderRequest.setAttribute("fileApprovalListCount", approvalDLFileEntries.size());

		} else if (Constants.TAB_UPLOAD_REVISE.equals(tab)) {
			long folderId = getParameterValue(renderRequest, "com.codevergence.btpn.neolink.revise.folderid");
			List<DLFileEntry> dlFiles = getFileListFromFolderId(folderId);
			List<Long> fileIds = new ArrayList<Long>();
			for(DLFileEntry filEntry : dlFiles){
				fileIds.add(filEntry.getFileEntryId());
			}
			List<PerijinanApproval> pApprovals = PerijinanApprovalServiceUtil.getService(renderRequest).getPerijinanApprovals(fileIds);
			renderRequest.setAttribute("fileReviseList", dlFiles);
			renderRequest.setAttribute("pApprovals", pApprovals);
			renderRequest.setAttribute("fileReviseListCount", dlFiles.size());
		}else if (Constants.TAB_REGION_MAPPING.equals(tab)) {
			
			//String tabRegionMapping = ParamUtil.getString(renderRequest, "tabs2", "PB");
			List<LineOfBusiness> lobs = LineOfBusinessServiceUtil.getService(renderRequest).getAll();
			String defaultLOB = ParameterServiceUtil.getService(renderRequest).getValue("N001.UNIT_BUSINESS");
			if(lobs != null && lobs.size() > 0){
				defaultLOB = lobs.get(0).getUnitBusinessId();
			}
			String tabRegionMapping = ParamUtil.getString(renderRequest, "tabs2", defaultLOB);
			
			StringBuilder lobTabs = new StringBuilder();
			for(int i = 0; i < lobs.size(); i++){
				LineOfBusiness lob = lobs.get(i);
				if(i != 0){
					lobTabs.append(",");
				}
				lobTabs.append(lob.getUnitBusinessId());
			}
			renderRequest.setAttribute("tabs2", lobTabs);
			LineOfBusiness lob = LineOfBusinessServiceUtil.getService(renderRequest).getByUnitBusinessId(tabRegionMapping);
			renderRequest.setAttribute("lineOfBusiness", lob);
			
			if (Constants.EDIT.equals(cmd)) {
				int provinceId = ParamUtil.getInteger(renderRequest, "id");
				String unitBusinessId = ParamUtil.getString(renderRequest, "unit-business-id");
				
				Province province = new Province();
				if (provinceId != 0) {
					province = ProvinceServiceUtil.getService(renderRequest).withId(provinceId);
				}
				renderRequest.setAttribute("province", province);
				
				List<Region> result = RegionServiceUtil.getService(renderRequest).getAll();
				renderRequest.setAttribute("regions", result);
				
				List<RegionMapping> mappings = RegionMappingServiceUtil.getService(renderRequest)
						.getRegionMapping(unitBusinessId, provinceId);
				renderRequest.setAttribute("mappings", mappings);
			} else {
				if(lobs.size() > 0){
					List<Province> result = ProvinceServiceUtil.getService(renderRequest).getAll();
					renderRequest.setAttribute("provinces", result);
				}
			}

		}

		super.doView(renderRequest, renderResponse);
	}


	public void saveRegion(ActionRequest actionRequest, ActionResponse actionResponse) {
		long id = ParamUtil.getLong(actionRequest, "id");
		Region region = new Region();
		region.setRegionCode(ParamUtil.getString(actionRequest, "region-code"));
		region.setName(ParamUtil.getString(actionRequest, "region-name"));
		if (id > 0) {
			//update
			region.setId(id);
			Region oldRegion = RegionServiceUtil.getService(actionRequest).withId(id);
			BranchServiceUtil.getService(actionRequest).updateRegion(oldRegion.getName(), region.getName());
			RegionServiceUtil.getService(actionRequest).update(region);
		} else {
			//insert
			RegionServiceUtil.getService(actionRequest).insert(region);
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_REGION);
	}
	
	public void deleteRegion(ActionRequest actionRequest, ActionResponse actionResponse) {
		long id = ParamUtil.getLong(actionRequest, "id");
		RegionServiceUtil.getService(actionRequest).delete(id);
		actionResponse.setRenderParameter("tabs1", Constants.TAB_REGION);
	}
	
	public void saveDataParameter(ActionRequest actionRequest, ActionResponse actionResponse) {
		String param = ParamUtil.getString(actionRequest, "param");
		String value = ParamUtil.getString(actionRequest, "value");

		Parameter parameter = new Parameter();
		parameter.setParam(param);
		parameter.setValue(value);
		ParameterServiceUtil.getService(actionRequest).save(parameter);
		actionResponse.setRenderParameter("tabs1", Constants.TAB_PARAMETER);
	}
	
	public void saveEmailScheduler(ActionRequest actionRequest, ActionResponse actionResponse) {
		long id = ParamUtil.getLong(actionRequest, "id");
		EmailScheduler emailScheduler = new EmailScheduler();
		emailScheduler.setName(ParamUtil.getString(actionRequest, "name"));
		emailScheduler.setEmailTemplateId(ParamUtil.getLong(actionRequest, "email-template"));
		emailScheduler.setEmailAddress(ParamUtil.getString(actionRequest, "admin-email-address"));
		emailScheduler.setPeriodeSendForAdmin(ParamUtil.getString(actionRequest, "admin-periode"));
		emailScheduler.setAmountSendPeriodForAdmin(ParamUtil.getInteger(actionRequest, "admin-amount-periode"));
		emailScheduler.setPeriodeSendBeforeLimit(ParamUtil.getString(actionRequest, "pic-send-before-limit"));
		emailScheduler.setAmountSendPeriodBeforeLimit(ParamUtil.getInteger(actionRequest, "pic-amount-before-limit"));
		emailScheduler.setPeriodeSendAfterLimit(ParamUtil.getString(actionRequest, "pic-send-after-limit"));
		emailScheduler.setAmountSendPeriodAfterLimit(ParamUtil.getInteger(actionRequest, "pic-amount-after-limit"));
		emailScheduler.setDescription(ParamUtil.getString(actionRequest, "keterangan"));
		
		if(id > 0){
			emailScheduler.setId(id);
			EmailSchedulerServiceUtil.getService(actionRequest).update(emailScheduler);
		}else{
			EmailSchedulerServiceUtil.getService(actionRequest).insert(emailScheduler);
		}

		actionResponse.setRenderParameter("tabs1", Constants.TAB_EMAIL_SCHEDULER);
	}
	
	public void saveBranchType(ActionRequest actionRequest, ActionResponse actionResponse) {
		long id = ParamUtil.getLong(actionRequest, "id");
		BranchType branchType = new BranchType();
		branchType.setBranchTypeId(ParamUtil.getString(actionRequest, "type-id"));
		branchType.setBranchTypeName(ParamUtil.getString(actionRequest, "name"));
		branchType.setBranchTypeMapMarker(ParamUtil.getString(actionRequest, "map-marker"));
		branchType.setShowOrder(ParamUtil.getInteger(actionRequest, "show-order"));
		branchType.setSandiKantor(ParamUtil.getString(actionRequest, "sandi-kantor"));
		branchType.setColorCode(ParamUtil.getString(actionRequest, "color-code"));
		branchType.setPerhitunganAmi(ParamUtil.getBoolean(actionRequest, "perhitungan-ami"));
		branchType.setMapView(ParamUtil.getBoolean(actionRequest, "map-view"));
		
		if(id > 0){
			branchType.setRowId(id);
			BranchType oldBranchType = BranchTypeServiceUtil.getService(actionRequest).withId(id);
			BranchServiceUtil.getService(actionRequest).updateBranchType(oldBranchType.getBranchTypeId(), branchType.getBranchTypeId());
			BranchTypeServiceUtil.getService(actionRequest).update(branchType);
		}else{
			BranchTypeServiceUtil.getService(actionRequest).insert(branchType);
		}
		
		actionResponse.setRenderParameter("tabs1", Constants.TAB_BRANCH_TYPE);
	}

	public void saveBranchClass(ActionRequest actionRequest, ActionResponse actionResponse) {
		int rowId = ParamUtil.getInteger(actionRequest, "rowId");
		BranchClass branchClass = new BranchClass();
		branchClass.setBranchClassId(ParamUtil.getString(actionRequest, "branchClassId"));
		branchClass.setBranchClassName(ParamUtil.getString(actionRequest, "branchClassName"));
		branchClass.setBranchClassMapMarker(ParamUtil.getString(actionRequest, "branchClassMapMarker"));

		if (rowId > 0) {
			branchClass.setRowId(rowId);
			BranchClass oldBranchClass = BranchClassServiceUtil.getService(actionRequest).getBranchClassById(rowId);
			BranchServiceUtil.getService(actionRequest).updateBranchClass(oldBranchClass.getBranchClassId(), branchClass.getBranchClassId());
			BranchClassServiceUtil.getService(actionRequest).update(branchClass);
		} else {
			BranchClassServiceUtil.getService(actionRequest).insert(branchClass);
		}

		actionResponse.setRenderParameter("tabs1", Constants.TAB_BRANCH_CLASS);
	}

	public void saveInvestStandardBI(ActionRequest actionRequest, ActionResponse actionResponse) {
		long id = ParamUtil.getLong(actionRequest, "id");
		InvestStandardBI investStandardBI = new InvestStandardBI();
		investStandardBI.setBookType(ParamUtil.getString(actionRequest, "book-type"));
		investStandardBI.setBranchTypeId(ParamUtil.getLong(actionRequest, "branch-type"));
		investStandardBI.setInvestValue(ParamUtil.getDouble(actionRequest, "invest-value"));
		investStandardBI.setName(ParamUtil.getString(actionRequest, "name"));
		if(id > 0){
			investStandardBI.setId(id);
			InvestStandardBIServiceUtil.getService(actionRequest).update(investStandardBI);
		}else{
			InvestStandardBIServiceUtil.getService(actionRequest).insert(investStandardBI);
		}
		
		actionResponse.setRenderParameter("tabs1", Constants.TAB_INVEST_STANDARD_BI);
	}
	
	public void saveZonaBI(ActionRequest actionRequest, ActionResponse actionResponse) {
		long id = ParamUtil.getLong(actionRequest, "id");
		String namaZona = ParamUtil.getString(actionRequest, "nama-zona");
		String colorCode = ParamUtil.getString(actionRequest, "color-code");

		ZonaBI zonaBi = new ZonaBI();
		zonaBi.setNamaZona(namaZona);
		zonaBi.setColorCode(colorCode);
		zonaBi.setKoefisien(ParamUtil.getDouble(actionRequest, "koefisien"));
		
		if (id > 0) {
			zonaBi.setId(id);
			ZonaBIServiceUtil.getService(actionRequest).update(zonaBi);
		} else {
			ZonaBIServiceUtil.getService(actionRequest).insert(zonaBi);
		}
		
		actionResponse.setRenderParameter("tabs1", Constants.TAB_ZONA_BI);
	}
	
	public void saveProvince(ActionRequest actionRequest, ActionResponse actionResponse) {
		long id = ParamUtil.getLong(actionRequest, "id");

		Province province = new Province();
		province.setColorCode(ParamUtil.getString(actionRequest, "color-code"));
		province.setImageId(ParamUtil.getString(actionRequest, "image-id"));
		province.setPropinsiId(ParamUtil.getString(actionRequest, "provinsi-id"));
		province.setPropinsiName(ParamUtil.getString(actionRequest, "name"));
		province.setZonaBiId(ParamUtil.getLong(actionRequest, "zona-bi"));
//		province.setRegionId(ParamUtil.getLong(actionRequest, "region"));
//		Region region = RegionServiceUtil.getService(actionRequest).withId(province.getRegionId());
//		province.setRegion(region.getName());
		if (id > 0) {
			province.setRowId(id);
			Province oldProvince = ProvinceServiceUtil.getService(actionRequest).withId(id);
			//update province when province is update via neolink administration
			BranchServiceUtil.getService(actionRequest).updatePropinsi(oldProvince.getPropinsiName(), province.getPropinsiName(), province.getPropinsiId());
			ProvinceServiceUtil.getService(actionRequest).update(province);
		} else {
			ProvinceServiceUtil.getService(actionRequest).insert(province);
		}
		
		actionResponse.setRenderParameter("tabs1", Constants.TAB_PROVINCE);
	}
	
	public void deleteZonaBI(ActionRequest actionRequest, ActionResponse actionResponse) {
		try{
			long id = ParamUtil.getLong(actionRequest, "id");
			ZonaBIServiceUtil.getService(actionRequest).delete(id);
		}catch(Exception e){
			if(e instanceof DataIntegrityViolationException){
				SessionErrors.add(actionRequest, "DATAISUSED");
			}else{
				SessionErrors.add(actionRequest, "UNEXPECTEDERROR");
				e.printStackTrace();
			}
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_ZONA_BI);
	}
	
	public void deleteProvince(ActionRequest actionRequest, ActionResponse actionResponse) {
		try{
			long id = ParamUtil.getLong(actionRequest, "id");
			ProvinceServiceUtil.getService(actionRequest).delete(id);
		}catch(Exception e){
			if(e instanceof DataIntegrityViolationException){
				SessionErrors.add(actionRequest, "DATAISUSED");
			}else{
				SessionErrors.add(actionRequest, "UNEXPECTEDERROR");
				e.printStackTrace();
			}
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_PROVINCE);
	}
	
	public void deleteinvestStandardBI(ActionRequest actionRequest, ActionResponse actionResponse) {
		try{
			long id = ParamUtil.getLong(actionRequest, "id");
			InvestStandardBIServiceUtil.getService(actionRequest).delete(id);
		}catch(Exception e){
			if(e instanceof DataIntegrityViolationException){
				SessionErrors.add(actionRequest, "DATAISUSED");
			}else{
				SessionErrors.add(actionRequest, "UNEXPECTEDERROR");
				e.printStackTrace();
			}
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_INVEST_STANDARD_BI);
	}
	
	public void deleteBranchType(ActionRequest actionRequest, ActionResponse actionResponse) {
		try{
			long id = ParamUtil.getLong(actionRequest, "id");
			BranchTypeServiceUtil.getService(actionRequest).delete(id);
		}catch(Exception e){
			if(e instanceof DataIntegrityViolationException){
				SessionErrors.add(actionRequest, "DATAISUSED");
			}else{
				SessionErrors.add(actionRequest, "UNEXPECTEDERROR");
				e.printStackTrace();
			}
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_BRANCH_TYPE);
	}

	public void deleteBranchClass(ActionRequest actionRequest, ActionResponse actionResponse) {
		try {
			int id = ParamUtil.getInteger(actionRequest, "id");
			BranchClassServiceUtil.getService(actionRequest).delete(id);
		} catch(Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				SessionErrors.add(actionRequest, "DATAISUSED");
			} else {
				SessionErrors.add(actionRequest, "UNEXPECTEDERROR");
				e.printStackTrace();
			}
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_BRANCH_CLASS);
	}

	public void saveLOB(ActionRequest actionRequest, ActionResponse actionResponse) {
		int id = ParamUtil.getInteger(actionRequest, "id");
		String unitBusinessId = ParamUtil.getString(actionRequest, "unit-business-id");
		String unitBusiness = ParamUtil.getString(actionRequest, "unit-business");
		String colorCode = ParamUtil.getString(actionRequest, "color-code");
		String emailGroup = ParamUtil.getString(actionRequest, "email-group");
		String showOrder = ParamUtil.getString(actionRequest, "show-order");

		LineOfBusiness lob = new LineOfBusiness();
		lob.setUnitBusinessId(unitBusinessId);
		lob.setUnitBusiness(unitBusiness);
		lob.setColorCode(colorCode);
		lob.setEmailGroup(emailGroup);
		lob.setShowOrder(showOrder);
		if(id > 0){
			lob.setId(id);
			LineOfBusiness oldLOB = LineOfBusinessServiceUtil.getService(actionRequest).getById(id);
			//Update Neolink_Master_Branch set unit_business_id = lob.getUnitBusinessId() getwhere old unit_business_id = oldLOB.getUnitBusinessId()
			BranchServiceUtil.getService(actionRequest).updateUnitBusinessId(oldLOB.getUnitBusinessId(), lob.getUnitBusinessId());
			LineOfBusinessServiceUtil.getService(actionRequest).update(lob);
		}else{
			LineOfBusinessServiceUtil.getService(actionRequest).insert(lob);
		}
		//regions
		/*if(id > 0){
			//only save regions when unit business is already in the database.
			List<Province> provinces = ProvinceServiceUtil.getService(actionRequest).getAll();
			for (int i = 0; i < provinces.size(); i++) {
				Province province = provinces.get(i);

				Region region = new Region();
				region.setProvinceId(province.getRowId());
				region.setProvinceName(province.getPropinsiName());
				
				region.setUnitBusinessId(id);
				region.setUnitBusinessName(unitBusiness);
				
				String regionsInfo = ParamUtil.getString(actionRequest, "regions"+province.getRowId());
				
				if(!regionsInfo.isEmpty()){
					region.setId(new Long(regionsInfo));
					long mapperId = ParamUtil.getLong(actionRequest, "province-mapper-id-"+province.getRowId() );
					if(mapperId < 0){
						//update
						RegionServiceUtil.getService(actionRequest).insertForMapping(region);
					} else {
						//insert
						region.setMappingId(mapperId);
						RegionServiceUtil.getService(actionRequest).updateForMapping(region);
					}
				} 
				
			}	
		}*/
		actionResponse.setRenderParameter("tabs1", Constants.TAB_LOB);
	}
	
	public void deleteLOB(ActionRequest actionRequest, ActionResponse actionResponse) {
		try{
			long id = ParamUtil.getLong(actionRequest, "id");
			LineOfBusinessServiceUtil.getService(actionRequest).delete(id);
			RegionServiceUtil.getService(actionRequest).deleteForMappingForLOBId(id);
		}catch(Exception e){
			if(e instanceof DataIntegrityViolationException){
				SessionErrors.add(actionRequest, "DATAISUSED");
			}else{
				SessionErrors.add(actionRequest, "UNEXPECTEDERROR");
				e.printStackTrace();
			}
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_LOB);
	}
	
	public void saveEmailTemplate(ActionRequest actionRequest, ActionResponse actionResponse){
		long id = ParamUtil.getLong(actionRequest, "id");
		String templateName = ParamUtil.getString(actionRequest, "template-name");
		String templateSubject = ParamUtil.getString(actionRequest, "template-subject");
		String templateBody = ParamUtil.getString(actionRequest, "template-body");
		EmailTemplate emailTemplate = new EmailTemplate();
		emailTemplate.setName(templateName);
		emailTemplate.setSubject(templateSubject);
		emailTemplate.setBody(templateBody);
		if(id > 0){
			emailTemplate.setId(id);
			EmailTemplateServiceUtil.getService(actionRequest).update(emailTemplate);
		}else{
			EmailTemplateServiceUtil.getService(actionRequest).insert(emailTemplate);
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_EMAIL_TEMPLATE);
	}
	
	public void deleteEmailTemplate(ActionRequest actionRequest, ActionResponse actionResponse){
		try{
			long id = ParamUtil.getLong(actionRequest, "id");
			EmailTemplateServiceUtil.getService(actionRequest).delete(id);
		}catch(Exception e){
			if(e instanceof DataIntegrityViolationException){
				SessionErrors.add(actionRequest, "DATAISUSED");
			}else{
				SessionErrors.add(actionRequest, "UNEXPECTEDERROR");
				e.printStackTrace();
			}
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_EMAIL_TEMPLATE);
	}
	
	public void deleteEmailScheduler(ActionRequest actionRequest, ActionResponse actionResponse){
		try{
			long id = ParamUtil.getLong(actionRequest, "id");
			EmailSchedulerServiceUtil.getService(actionRequest).delete(id);
		}catch(Exception e){
			if(e instanceof DataIntegrityViolationException){
				SessionErrors.add(actionRequest, "DATAISUSED");
			}else{
				SessionErrors.add(actionRequest, "UNEXPECTEDERROR");
				e.printStackTrace();
			}
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_EMAIL_SCHEDULER);
	}
	
	public void savePerijinanBI(ActionRequest actionRequest, ActionResponse actionResponse){
		long id = ParamUtil.getLong(actionRequest, "id");
		String namaDokumen = ParamUtil.getString(actionRequest, "name-document");
		String noDokumen = ParamUtil.getString(actionRequest, "no-document");
		boolean isEfective = ParamUtil.getBoolean(actionRequest, "efective");
		boolean isActive = ParamUtil.getBoolean(actionRequest, "release");
		
		int startDateMonth = ParamUtil.getInteger(actionRequest, "month1");
		int startDateDay = ParamUtil.getInteger(actionRequest, "day1");
		int startDateYear = ParamUtil.getInteger(actionRequest, "year1");
		
		int endDateMonth = ParamUtil.getInteger(actionRequest, "month2");
		int endDateDay = ParamUtil.getInteger(actionRequest, "day2");
		int endDateYear = ParamUtil.getInteger(actionRequest, "year2");

		Date keluarDokumenDate = null;
		Date berakhirDokumenDate = null;
		try {
			keluarDokumenDate = PortalUtil.getDate(startDateMonth, startDateDay, startDateYear, new PortalException());
			if(isEfective){
				berakhirDokumenDate = PortalUtil.getDate(endDateMonth, endDateDay, endDateYear, new PortalException());
				if(berakhirDokumenDate != null){
					PerijinanBIServiceUtil.getService(actionRequest).updateOpeningDateMasterBranch(berakhirDokumenDate, id);
				}
			}
			
		} catch (PortalException e) {
			
		}
		
		PerijinanBI perijinan = new PerijinanBI();
		perijinan.setNamaDokumen(namaDokumen);
		perijinan.setNoDokumen(noDokumen);
		perijinan.setTglKeluarDokumen(keluarDokumenDate);
		perijinan.setTglBerakhirDokumen(berakhirDokumenDate);
		perijinan.setActive(isActive);
		perijinan.setEfective(isEfective);
		
		if(id > 0){
			perijinan.setId(id);
			PerijinanBIServiceUtil.getService(actionRequest).update(perijinan);
			
			if(!isActive){
				PerijinanBIServiceUtil.getService(actionRequest).toggleRecap(false, id);
			}
			if(!isEfective){
				PerijinanBIServiceUtil.getService(actionRequest).toggleRecapEfective(false, id);
			}
		}else{
			PerijinanBIServiceUtil.getService(actionRequest).insert(perijinan);
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_PERIJINAN);
		actionResponse.setRenderParameter("tabs2", Constants.TAB_PERIJINAN_BI);
	}
	
	public void deletePerijinanBI(ActionRequest actionRequest, ActionResponse actionResponse){
		long id = ParamUtil.getLong(actionRequest, "id");
		
		if(id > 0){
			PerijinanBI perijinan = PerijinanBIServiceUtil.getService(actionRequest).getById(id);
			try {
				//delete DLFileEntry first
				DLFileEntryLocalServiceUtil.deleteDLFileEntry(perijinan.getFileId());
				
				//then delete data
				PerijinanBIServiceUtil.getService(actionRequest).delete(perijinan.getId());
			} catch (Exception e) {
				if(e instanceof NoSuchFileEntryException){
					//then delete data
					PerijinanBIServiceUtil.getService(actionRequest).delete(perijinan.getId());
				}else{
					e.printStackTrace();
				}
			}
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_PERIJINAN);
		actionResponse.setRenderParameter("tabs2", Constants.TAB_PERIJINAN_BI);
	}
	
	public void savePerijinanUmum(ActionRequest actionRequest, ActionResponse actionResponse){
		long id = ParamUtil.getLong(actionRequest, "id");
		String namaDokumen = ParamUtil.getString(actionRequest, "name-document");
		String noDokumen = ParamUtil.getString(actionRequest, "no-document");
		boolean isActive = ParamUtil.getBoolean(actionRequest, "active");
		
		int startDateMonth = ParamUtil.getInteger(actionRequest, "month1");
		int startDateDay = ParamUtil.getInteger(actionRequest, "day1");
		int startDateYear = ParamUtil.getInteger(actionRequest, "year1");
		
		int endDateMonth = ParamUtil.getInteger(actionRequest, "month2");
		int endDateDay = ParamUtil.getInteger(actionRequest, "day2");
		int endDateYear = ParamUtil.getInteger(actionRequest, "year2");

		Date keluarDokumenDate = null;
		Date berakhirDokumenDate = null;
		try {
			keluarDokumenDate = PortalUtil.getDate(startDateMonth, startDateDay, startDateYear, new PortalException());
			berakhirDokumenDate = PortalUtil.getDate(endDateMonth, endDateDay, endDateYear, new PortalException());
		} catch (PortalException e) {
			
		}
		
		PerijinanUmum perijinan = new PerijinanUmum();
		perijinan.setNamaDokumen(namaDokumen);
		perijinan.setNoDokumen(noDokumen);
		perijinan.setTglKeluarDokumen(keluarDokumenDate);
		perijinan.setTglBerakhirDokumen(berakhirDokumenDate);
		perijinan.setActive(isActive);
		
		if(id > 0){
			perijinan.setId(id);
			PerijinanUmumServiceUtil.getService(actionRequest).update(perijinan);
		}else{
			PerijinanUmumServiceUtil.getService(actionRequest).insert(perijinan);
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_PERIJINAN);
		actionResponse.setRenderParameter("tabs2", Constants.TAB_PERIJINAN_UMUM);
	}
	
	public void deletePerijinanUmum(ActionRequest actionRequest, ActionResponse actionResponse){
		long id = ParamUtil.getLong(actionRequest, "id");
		
		if(id > 0){
			PerijinanUmum perijinan = PerijinanUmumServiceUtil.getService(actionRequest).getById(id);
			try {
				//delete DLFileEntry first
				DLFileEntryLocalServiceUtil.deleteDLFileEntry(perijinan.getFileId());
				
				//then delete data
				PerijinanUmumServiceUtil.getService(actionRequest).delete(perijinan.getId());
			} catch (Exception e) {
				if(e instanceof NoSuchFileEntryException){
					//then delete data
					PerijinanUmumServiceUtil.getService(actionRequest).delete(perijinan.getId());
				}else{
					e.printStackTrace();
				}
			}
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_PERIJINAN);
		actionResponse.setRenderParameter("tabs2", Constants.TAB_PERIJINAN_UMUM);
	}
	
	public void saveKabupatenKota(ActionRequest actionRequest, ActionResponse actionResponse){
		int id = ParamUtil.getInteger(actionRequest, "id");
		
		int provinsiId = ParamUtil.getInteger(actionRequest, "provinsi");
		String kabupatenCode = ParamUtil.getString(actionRequest, "kabupaten-code");
		String kabupatenName = ParamUtil.getString(actionRequest, "kabupaten-name");
		boolean active = ParamUtil.getBoolean(actionRequest, "active");
		
		Province province = ProvinceServiceUtil.getService(actionRequest).withId(provinsiId);
		
		KabupatenKota kabupatenKota = new KabupatenKota();
		kabupatenKota.setKabupatenId(kabupatenCode);
		kabupatenKota.setName(kabupatenName);
		kabupatenKota.setProvinsi(province);
		kabupatenKota.setActive(active);
		boolean validForUpdate = false;
		
		KabupatenKota checkExisting = KabupatenKotaServiceUtil.getService(actionRequest).withIdAndName(kabupatenCode, kabupatenName);
		if(checkExisting != null){
			if(checkExisting.getId() > 0){
				validForUpdate = true;
				kabupatenKota.setId(checkExisting.getId());
			}
		}
		
		if(id > 0){
			kabupatenKota.setId(id);
		}
		
		if(validForUpdate || id > 0){
			KabupatenKotaServiceUtil.getService(actionRequest).update(kabupatenKota);
		}else{
			KabupatenKotaServiceUtil.getService(actionRequest).insert(kabupatenKota);
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_KABUPATEN);
	}
	
	public void toggleActiveKabupaten(ActionRequest actionRequest, ActionResponse actionResponse){
		int id = ParamUtil.getInteger(actionRequest, "id");
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		boolean active = true;
		if(cmd.equals(Constants.DEACTIVATE)){
			active = false;
		}else if(cmd.equals(Constants.RESTORE)){
			active = true;
		}
		if(id > 0){
			KabupatenKotaServiceUtil.getService(actionRequest).active(id, active);
		}
		actionResponse.setRenderParameter("tabs1", Constants.TAB_KABUPATEN);
	}

	@Override
	public void serveResource(ResourceRequest resourceRequest, ResourceResponse resourceResponse) 
			throws PortletException ,IOException {

		if (resourceRequest.getResourceID() != null) {

			if(resourceRequest.getResourceID().equals("excel")){
				generateExcelForKabupaten(resourceRequest, resourceResponse);
			} else if (resourceRequest.getResourceID().equals("excel_error_report")){
				generateExcelForJaringanKantorExcelReport(resourceRequest, resourceResponse);
			}
		}
	}

	
	private void generateExcelForJaringanKantorExcelReport(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow headerRow = null;

		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);

		headerRow = sheet.createRow(0);
		createCellHeader(headerRow, 0, "No", style);
		createCellHeader(headerRow, 1, "Branch ID", style);
		createCellHeader(headerRow, 2, "Parent ID", style);
		createCellHeader(headerRow, 3, "Branch Class ID", style);
		
		createCellHeader(headerRow, 4, "Kabupaten/Kota ID", style);
		createCellHeader(headerRow, 5, "Propinsi ID", style);

		int rowNumber = 1;
		HSSFRow row = null;

		List<ExcelReport> result = ExcelReportServiceUtil.getService(resourceRequest).getAll();
		
		for (ExcelReport excelReport : result) {
			row = sheet.createRow(rowNumber);
			row.createCell(0).setCellValue(rowNumber);
			row.createCell(1).setCellValue(excelReport.getBranchId());
			row.createCell(2).setCellValue(excelReport.getParentId());
			row.createCell(3).setCellValue(excelReport.getBranchClassId());
			row.createCell(4).setCellValue(excelReport.getKabupatenKota());
			row.createCell(5).setCellValue(excelReport.getPropinsi());
			rowNumber++;
		}
		
		String filename = "error-report-jaringan-kantor.xls";
		resourceResponse.setContentType("application/vnd.ms-excel");
		resourceResponse.setProperty("Content-Disposition", "attachment;filename=\"" + filename + "\"");

		workbook.write(resourceResponse.getPortletOutputStream());

	}
	
	private void generateExcelForKabupaten(ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws IOException {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow headerRow = null;

		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		style.setFont(font);

		headerRow = sheet.createRow(0);
		createCellHeader(headerRow, 0, "No", style);
		createCellHeader(headerRow, 1, "Kode Provinsi", style);
		createCellHeader(headerRow, 2, "Nama Provinsi", style);
		createCellHeader(headerRow, 3, "Kode Dati II", style);
		createCellHeader(headerRow, 4, "Nama Dati II", style);

		int rowNumber = 1;
		HSSFRow row = null;

		List<KabupatenKota> result = KabupatenKotaServiceUtil.getService(resourceRequest).getAll();
		for (KabupatenKota kabupaten : result) {
			row = sheet.createRow(rowNumber);
			row.createCell(0).setCellValue(rowNumber);
			row.createCell(1).setCellValue(kabupaten.getPropinsiId());
			row.createCell(2).setCellValue(kabupaten.getProvinsi().getPropinsiName());
			row.createCell(3).setCellValue(kabupaten.getKabupatenId());
			row.createCell(4).setCellValue(kabupaten.getName());
			rowNumber++;
		}
		
		String filename = "List-Kabupaten.xls";
		resourceResponse.setContentType("application/vnd.ms-excel");
		resourceResponse.setProperty("Content-Disposition", "attachment;filename=\"" + filename + "\"");

		workbook.write(resourceResponse.getPortletOutputStream());
	}

	private static Cell createCellHeader(HSSFRow headerRow, int column, String title, CellStyle style) {
		Cell cellHeader = headerRow.createCell(column);
		cellHeader.setCellValue(title);
		cellHeader.setCellStyle(style);
		return cellHeader;
	}

	private List<DLFileEntry> getFileListFromFolderId(long folderId) {
		List<DLFileEntry> dLFiles = null;
		try {
			DLFolder uploadFolder = DLFolderLocalServiceUtil.getFolder(folderId);
			dLFiles = DLFileEntryLocalServiceUtil.getFileEntries(uploadFolder.getGroupId(), folderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dLFiles;
	}

	//	public void reviseApproval(ActionRequest actionRequest, ActionResponse actionResponse) {
//		System.out.println("revise");
//
//		String[] fileIds = ParamUtil.getParameterValues(actionRequest, "fileId");
//		long newFolderId = getParameterValue(actionRequest, "com.codevergence.btpn.neolink.revise.folderid");
//		for (int i = 0; i < fileIds.length; i++) {
//			System.out.println(i + ": " + fileIds[i]);
//			String[] fileInfos = fileIds[i].split("\\|");
//			ServiceContext serviceContext = new ServiceContext();
//			try {
//			DLFileEntry dLFileEntryMoved = DLFileEntryServiceUtil.moveFileEntry(
//					Long.parseLong(fileInfos[0]), 
//					Long.parseLong(fileInfos[1]),
//					newFolderId, 
//					fileInfos[2], 
//					serviceContext);
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		}
//
//		actionResponse.setRenderParameter("tabs1", Constants.TAB_UPLOAD_APPROVAL);
//	}

//	public void approveApproval(ActionRequest actionRequest, ActionResponse actionResponse) {
//		System.out.println("approve");
//
//		String[] fileIds = ParamUtil.getParameterValues(actionRequest, "fileId");
//		long newFolderId = getParameterValue(actionRequest, "com.codevergence.btpn.neolink.approved.folderid");
//		for (int i = 0; i < fileIds.length; i++) {
//			System.out.println(i + ": " + fileIds[i]);
//			String[] fileInfos = fileIds[i].split("\\|");
//			ServiceContext serviceContext = new ServiceContext();
//			try {
//			DLFileEntry dLFileEntryMoved = DLFileEntryServiceUtil.moveFileEntry(
//					Long.parseLong(fileInfos[0]), 
//					Long.parseLong(fileInfos[1]),
//					newFolderId, 
//					fileInfos[2], 
//					serviceContext);
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		}
//
//    	actionResponse.setRenderParameter("tabs1", Constants.TAB_UPLOAD_APPROVAL);
//	}
	
	public void saveRegionMapping(ActionRequest actionRequest, ActionResponse actionResponse){
		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");
		
		long provinceId = ParamUtil.getLong(actionRequest, "province-id");
		String unitBusinessId = ParamUtil.getString(actionRequest, "unit-business-id");
		long[] regionIds = ParamUtil.getLongValues(actionRequest, "region-id");
		if(regionIds.length > 0){
			RegionMappingServiceUtil.getService(actionRequest).deleteRegionMapping(provinceId, unitBusinessId);
			for (int i = 0; i < regionIds.length; i++) {
				RegionMapping regionMapping = new RegionMapping();
				regionMapping.setId(regionIds[i]);
				
				Province province = new Province();
				province.setRowId(provinceId);
				regionMapping.setProvinse(province);
				
				LineOfBusiness lob = new LineOfBusiness();
				lob.setUnitBusinessId(unitBusinessId);
				regionMapping.setLob(lob);
				
				RegionMappingServiceUtil.getService(actionRequest).save(regionMapping);
			}
		}
		
		actionResponse.setRenderParameter("tabs1", Constants.TAB_REGION_MAPPING);
		actionResponse.setRenderParameter("tabs2", tabs2);
	}
	
	public void deleteRegionMapping(ActionRequest actionRequest, ActionResponse actionResponse){
		String tabs2 = ParamUtil.getString(actionRequest, "tabs2");
		
		long provinceId = ParamUtil.getLong(actionRequest, "id");
		String unitBusinessId = ParamUtil.getString(actionRequest, "unit-business-id");
		
		if(provinceId > 0){
			RegionMappingServiceUtil.getService(actionRequest).deleteRegionMapping(provinceId, unitBusinessId);
		}
		
		actionResponse.setRenderParameter("tabs1", Constants.TAB_REGION_MAPPING);
		actionResponse.setRenderParameter("tabs2", tabs2);
	}

	private long getParameterValue(PortletRequest portletRequest, String param) {
		String strFolderId = ParameterServiceUtil.getService(portletRequest).getValue(param);
		long folderId = Long.parseLong(strFolderId);
		return folderId;
	}
	
	public static final Log _log = LogFactoryUtil.getLog(NeoLinkAdministrationPortlet.class);
}
