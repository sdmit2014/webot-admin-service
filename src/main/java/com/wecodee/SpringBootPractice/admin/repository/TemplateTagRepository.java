package com.wecodee.SpringBootPractice.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wecodee.SpringBootPractice.admin.model.TemplateTag;

public interface TemplateTagRepository extends BaseRepository<TemplateTag> {

	List<TemplateTag> getByNotificationType(String notificationType);

    @Query("FROM TemplateTag WHERE notificationType IN ('COMMON_TYPE','COMMON_CUSTOMER',:notificationType)")
	List<TemplateTag> getAllNotificationType(@Param("notificationType") String notificationType);

}
