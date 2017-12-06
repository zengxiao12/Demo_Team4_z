package cn.bdqn.datacockpit.mapper;

import java.util.List;

import cn.bdqn.datacockpit.entity.Analysistasks;

public interface AnalysistasksMapper {
    List<Analysistasks> selectAllTasks();
    
    int deleteByPrimaryKey(Integer id);

    int insert(Analysistasks record);

    int insertSelective(Analysistasks record);

    Analysistasks selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Analysistasks record);

    int updateByPrimaryKey(Analysistasks record);
    
    
}