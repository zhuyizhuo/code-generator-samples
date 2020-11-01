package com.github.generator.management.repository;

import java.util.List;

import com.github.generator.management.repository.model.SysDict;

/**
 * description : sys_dict table dao layer interface <br/>
 * time:    2020-11-01 15:40:16 <br/>
 * @author  TODO <br/>
 * @since   1.0 <br/>
 * @version 1.0 <br/>
 */
public interface SysDictMapper {

	/**
     * 新增数据 <br>
     * @param sysDict sys_dict 参数对象  <br>
     * @return 新增的数据条数
     */
	int insertSysDict(SysDict sysDict);

	/**
     * 根据主键删除数据 <br>
     * @param id 主键  <br>
     * @return 删除的数据条数
     */
    int deleteSysDictByPrimaryKey(Long id);

	/**
     * 根据传入参数删除数据 <br>
     * @param sysDict sys_dict 参数对象  <br>
     * @return 删除的数据条数
     */
    int deleteSysDictByWhere(SysDict sysDict);

	/**
     * 根据主键更新数据 <br>
     * @param sysDict sys_dict 参数对象  <br>
     * @return 更新的数据条数
     */
    int updateSysDictByPrimaryKey(SysDict sysDict);


	/**
     * 根据主键查询数据 <br>
     * @param id 主键  <br>
     * @return SysDict 数据对象
     */
    SysDict querySysDictByPrimaryKey(Long id);

	/**
     * 根据传入参数查询数据列表 <br>
     * @param sysDict sys_dict 参数对象  <br>
     * @return 符合条件的数据集合
     */
    List<SysDict> querySysDictListByWhere(SysDict sysDict);

	/**
     * 统计符合条件的数据数量 <br>
     * @param sysDict sys_dict 参数对象  <br>
     * @return 符合条件的数据总数
     */
    int countSysDictByWhere(SysDict sysDict);

	/**
     * 批量插入数据 <br>
     * @param sysDictList sys_dict 参数对象  <br>
     * @return 新增的数据条数
     */
    int batchInsertSysDict(List<SysDict> sysDictList);
}