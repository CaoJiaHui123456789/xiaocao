package com.cjxy.park.service.setUp;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.cjxy.park.bean.setUp.SysNewsTable;
import com.cjxy.park.dao.setUp.NewsTableDao;
import com.cjxy.park.dao.setUp.NewsTableRepository;


@Service
public class NewsTableService {

	@Resource
	private NewsTableRepository newsTableRepository;
	@Resource
	private NewsTableDao newsTableDao;
	
	//查询所有
	public List<SysNewsTable> queryAll() {
		
		return newsTableRepository.queryAllOrderByNewsRank();
	}
  
	//分页
	public Page<SysNewsTable> queryNewsTable(SysNewsTable table, int pageNo, int pageSize) throws Exception{
		
		return newsTableDao.queryNewsTable(table, pageNo, pageSize);
	}
	
	//保存
		public String saveNewsTable(SysNewsTable table) {

			if (null == table.getNewsCode() || "".equals(table.getNewsCode())) {
				return "新闻编号不能为空";
			} else {
				List<SysNewsTable> ls = newsTableRepository.findByNewsCode(table.getNewsCode());
				if (null != ls && ls.size() > 0) {
					return table.getNewsCode() + "已经存在";
				}
			}
			if (null == table.getNewsRank()) {
				return "新闻等级不能为空";
			}

			newsTableRepository.save(table);
			return "success";
		}

		// 修改信息
		public String updNewsTable(SysNewsTable table) {
			String newsCode = table.getNewsCode();
			List<SysNewsTable> lsList = newsTableRepository.findByNewsCode(newsCode);
			if (null == table.getNewsCode() || "".equals(table.getNewsCode())) {
				return "新闻编号不能为空";
			} else if (lsList != null && lsList.size() > 0) {
				SysNewsTable sNewsTable = lsList.get(0);
				if (sNewsTable.getNewsId() == table.getNewsId()) {
					
					if (null == table.getNewsRank()) {
						return "新闻等级不能为空";
					}
					newsTableRepository.save(table);
				} else {
					return newsCode + "已经存在";
				}

			} else {
				if (null == table.getNewsRank()) {
					return "新闻等级不能为空";
				}
				newsTableRepository.save(table);
			}
			return "success";
		}
		
		//删除
		public void delNewsTable(Integer newsId) {
			newsTableRepository.deleteById(newsId);
		}
		
}
