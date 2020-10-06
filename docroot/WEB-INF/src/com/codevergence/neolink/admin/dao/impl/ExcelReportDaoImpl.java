package com.codevergence.neolink.admin.dao.impl;

import com.codevergence.neolink.admin.dao.ExcelReportDao;
import com.codevergence.neolink.admin.model.ExcelReport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("excelReportDao")
public class ExcelReportDaoImpl implements ExcelReportDao {

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
//	truncate table Neolink_Excel_Report
	public void clearTable() throws DataAccessException{
		this.jdbcTemplate.execute("truncate table Neolink_Excel_Report");
		
	}

	
	public void insert(ExcelReport excelReport) throws DataAccessException{
/*		this.jdbcTemplate.update("insert into Neolink_Excel_Report (EXECUTE_DATE, BRANCH_ID, BRANCH_NAME, KABUPATEN_KOTA_ID, PROPINSI_ID, DESCRIPTION) values (?,?,?,?,?,?)", new Object[]{ 
				new Date(),
				excelReport.getBranchId(),
				excelReport.getBranchName(),
				excelReport.getKabupatenKota(),
				excelReport.getPropinsi(),
				excelReport.getDescription()
		});*/
		
	}

	
	public List<ExcelReport> getAll() throws DataAccessException {
		List<ExcelReport> results = new ArrayList<ExcelReport>();
		//results  = jdbcTemplate.query("SELECT distinct  BRANCH_ID  , KABUPATEN_KOTA_ID , PROPINSI_ID  FROM  Neolink_Excel_Report ", new ExcelReportMapper());
		String query = "SELECT T1.BRANCH_ID,T1.PROPINSI_ID,T1.KABUPATEN_ID,T1.PARENT_ID, "+
	" CASE WHEN  (	 "+ 	
	" SELECT COUNT(BRANCH_CLASS_ID) from Neolink_Master_Branch_Class  where BRANCH_CLASS_ID = BRANCH_CLASS_ID_TMP  ) > 0   "+
	" 	THEN BRANCH_CLASS_ID_TMP + 'Ada'  "+
	" 	ELSE BRANCH_CLASS_ID_TMP+ ' Tidak Ada'  "+
	" 	END  "+
	" AS BRANCH_CLASS_ID  "+
	" FROM ( 	 "+
	" 	SELECT T2.BRANCH_ID,T2.PROPINSI_ID,T2.KABUPATEN_ID, 	 "+
	" 	CASE WHEN  		 "+
	" 		( 			 "+
	" 		SELECT COUNT(BRANCH_ID) from Neolink_Master_Branch where BRANCH_ID = PARENT_ID_TMP ) >0   "+		
	" 			THEN PARENT_ID_TMP + ' Ada' 		 "+
	" 			ELSE PARENT_ID_TMP + ' Tidak Ada' 		 "+
	" 			END AS PARENT_ID, 	T2.BRANCH_CLASS_ID_TMP 	FROM ( 		 "+
	" 				SELECT  		 "+
	" 					BRANCH_ID,  		 "+
	" 					isnull(PROPINSI_ID,'TIDAK ADA') as PROPINSI_ID,  "+		
	" 					isnull(KABUPATEN_ID,'TIDAK ADA') as KABUPATEN_ID,   "+		
	" 					PARENT_ID AS PARENT_ID_TMP, 		 "+
	" 					BRANCH_CLASS_ID AS BRANCH_CLASS_ID_TMP 		 "+
	" 					from  	 "+
	" 					Neolink_Master_Branch a 		 "+
	" 					where  	 "+
	" 					a.PARENT_ID is null "+
	" 					or	 "+
	" 					a.PARENT_ID not in 		 "+
	" 					( select BRANCH_ID from Neolink_Master_Branch ) 	 "+	
	" 					or 		 "+
	" 					a.BRANCH_CLASS_ID not in 		 "+
	" 					( select BRANCH_CLASS_ID from Neolink_Master_Branch_Class) 	 "+
	" 					or "+
	" 					a.KABUPATEN_ID not in (select KABUPATEN_ID from Neolink_Master_Kabupaten) "+
	" 					or "+
	" 					a.KABUPATEN_ID is null "+
	" 				)  "+
	" 					AS T2  "+
	" 				)  "+
	" 			AS T1 ";
		results  = jdbcTemplate.query(query, new ExcelReportMapper());
		return results;
	}
	
	private static final class ExcelReportMapper implements RowMapper<ExcelReport> {
		public ExcelReport mapRow(ResultSet rs, int arg1) throws SQLException {
			ExcelReport excelReport = new ExcelReport();
//			excelReport.setRowId(rs.getInt("ROW_ID"));
//			excelReport.setErrorDate(rs.getDate("EXECUTE_DATE"));
			excelReport.setBranchId(rs.getString("BRANCH_ID"));
//			excelReport.setBranchName(rs.getString("BRANCH_NAME"));
			excelReport.setKabupatenKota(rs.getString("KABUPATEN_ID"));
			excelReport.setPropinsi(rs.getString("PROPINSI_ID"));
//			excelReport.setDescription(rs.getString("DESCRIPTION"));
			excelReport.setParentId(rs.getString("PARENT_ID"));
			excelReport.setBranchClassId(rs.getString("BRANCH_CLASS_ID"));
			return excelReport;
		}
	}

}
