package com.spring.service.config;

import static com.spring.util.Common.*;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.spring.dao.config.SiteConfigDAO;
import com.spring.service.admin.OperationRecordService;
import com.spring.vo.config.SiteConfigVO;

@Service("siteConfigServiceImpl")
public class SiteConfigServiceImpl implements SiteConfigService {

	@Autowired
    private SiteConfigDAO siteConfigDAO;
	@Autowired
    private OperationRecordService operationRecordService;
	@Autowired
    private MessageSource messageSource;
	
	@Transactional("transaction")
	@Override
	public Map<String, Object> modifySiteConfig(List<SiteConfigVO> siteConfigVOs, Locale locale) throws Exception {
		
		Iterator<SiteConfigVO> siteConfigIterator = siteConfigVOs.iterator();
		while (siteConfigIterator.hasNext()) {

			siteConfigDAO.modifySiteConfig(siteConfigIterator.next());
		}
		
		operationRecordService.createOperationRecord("修改了APP配置信息 ");
		
		return output("0", null, messageSource.getMessage("modify_seccess", null, locale));
	}
	@Override
	public List<SiteConfigVO> getSiteConfigs(SiteConfigVO siteConfigVO) {
		
		return siteConfigDAO.getSiteConfigs(siteConfigVO);
	}
}