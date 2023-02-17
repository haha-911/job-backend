package com.milk.job.model.dto;

import com.milk.job.model.pojo.Education;
import com.milk.job.model.pojo.Experience;
import com.milk.job.model.pojo.Project;
import com.milk.job.model.pojo.Resume;
import lombok.Data;

import java.util.List;

/**
 * @Description Is Description
 * @Author Milk
 * @Date 2022-12-10 15:54
 */
@Data
public class ResumeDto extends Resume {

    private List<Experience> experience;

    private List<Education> education;

    private List<Project> project;
}
