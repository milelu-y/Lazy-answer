package com.lazy.common.core.domain.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.lazy.common.annotation.Excel;
import com.lazy.common.core.domain.BaseEntity;

/**
 * 资源对象 sys_resource
 *
 * @author lazy
 * @date 2021-08-03
 */
@Data
@Accessors(chain = true)
public class SysResource extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 文件唯一标识 */
    @Excel(name = "文件唯一标识")
    private String fileUid;

    /** 文件名称 */
    @Excel(name = "文件名称")
    private String fileName;

    /** 标题 */
    @Excel(name = "标题")
    private String fileMd5;

    /** 文件大小kb */
    @Excel(name = "文件大小kb")
    private Long fileSize;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    /** url */
    @Excel(name = "url")
    private String url;

    /** 相对地址 */
    @Excel(name = "相对地址")
    private String filePath;

    /** 全地址 */
    @Excel(name = "全地址")
    private String fileFullPath;

    /** 组 */
    @Excel(name = "组")
    private String groupName;

    /** 创建用户id */
    @Excel(name = "创建用户id")
    private String createId;

    /** 修改人Id */
    @Excel(name = "修改人Id")
    private String modifiedId;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }
    public void setFileUid(String fileUid)
    {
        this.fileUid = fileUid;
    }

    public String getFileUid()
    {
        return fileUid;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setFileMd5(String fileMd5)
    {
        this.fileMd5 = fileMd5;
    }

    public String getFileMd5()
    {
        return fileMd5;
    }
    public void setFileSize(Long fileSize)
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize()
    {
        return fileSize;
    }
    public void setFileType(String fileType)
    {
        this.fileType = fileType;
    }

    public String getFileType()
    {
        return fileType;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getUrl()
    {
        return url;
    }
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getFilePath()
    {
        return filePath;
    }
    public void setFileFullPath(String fileFullPath)
    {
        this.fileFullPath = fileFullPath;
    }

    public String getFileFullPath()
    {
        return fileFullPath;
    }
    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public String getGroupName()
    {
        return groupName;
    }
    public void setCreateId(String createId)
    {
        this.createId = createId;
    }

    public String getCreateId()
    {
        return createId;
    }
    public void setModifiedId(String modifiedId)
    {
        this.modifiedId = modifiedId;
    }

    public String getModifiedId()
    {
        return modifiedId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileUid", getFileUid())
            .append("fileName", getFileName())
            .append("fileMd5", getFileMd5())
            .append("fileSize", getFileSize())
            .append("fileType", getFileType())
            .append("url", getUrl())
            .append("filePath", getFilePath())
            .append("fileFullPath", getFileFullPath())
            .append("groupName", getGroupName())
            .append("createId", getCreateId())
            .append("modifiedId", getModifiedId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
