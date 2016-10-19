package com.poppy.sport.vo;

import javax.servlet.http.HttpServletRequest;

/**
 * Datatables发送到服务器的数据,没有添加排序信息
 * 
 * @author chenb-c
 * @date 2016-08-16
 * 
 *
 */
public class DataTableParam {
	/**
	 * 绘制计数器,这个是用来确保Ajax从服务器返回的是对应的（Ajax是异步的，因此返回的顺序是不确定的）. 要求在服务器接收到此参数后再返回
	 */
	private int draw;
	/**
	 * 第一条数据的起始位置,比如0代表第一条数据
	 */
	private int start;
	/**
	 * 告诉服务器每页显示的条数,这个数字会等于返回的
	 * data集合的记录数,可能会大于因为服务器可能没有那么多数据,这个也可能是-1,代表需要返回全部数据(尽管这个和服务器处理的理念有点违背)
	 */
	private int length;

	/**
	 * 全局的搜索条件，条件会应用到每一列（ searchable需要设置为 true ）
	 */
	private String searchValue;

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public static DataTableParam wrapperParam(HttpServletRequest request) {
		DataTableParam param = new DataTableParam();
		String draw = request.getParameter("draw");
		String start = request.getParameter("start");
		String length = request.getParameter("length");
		String searchValue = request.getParameter("search[value]");
		param.setDraw(Integer.parseInt(draw));
		param.setStart(Integer.parseInt(start));
		param.setLength(Integer.parseInt(length));
		param.setSearchValue(searchValue);
		return param;
	}

	/*
	 * private String[] search; private String[][] columns; private String[][]
	 * order;
	 */
	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "DataTableParam [draw=" + draw + ", start=" + start + ", length=" + length + "]";
	}

}
