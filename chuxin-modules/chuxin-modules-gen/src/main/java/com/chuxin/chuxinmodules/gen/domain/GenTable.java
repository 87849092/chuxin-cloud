package com.chuxin.chuxinmodules.gen.domain;

import com.chuxin.chuxincommon.core.constant.GenConstants;
import com.chuxin.chuxincommon.core.web.domain.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author 初心
 * @date 2021/8/29 10:42
 */
@Setter
@Getter
public class GenTable extends BaseDTO {

    private static final long serialVersionUID = -4162015537128102293L;

    /** 表id*/
    private Long tableId;

    @NotBlank(message = "表名称不能为空")
    private String tableName;

    @NotBlank(message = "表描述不能为空")
    private String tableComment;

    @NotBlank(message = "实体类名称不能为空")
    private String className;

    @NotBlank(message = "生成包路径不能为空")
    private String packageName;

    @NotBlank(message = "生成模块名不能为空")
    private String moduleName;

    @NotBlank(message = "生成业务名不能为空")
    private String businessName;

    @NotBlank(message = "生成功能名不能为空")
    private String functionName;

    @NotBlank(message = "作者不能为空")
    private String functionAuthor;

    /** 生成代码方式（0zip压缩包 1自定义路径） */
    private String genType;

    /** 生成路径（不填默认项目路径） */
    private String genPath;

    /** 主键信息 */
    private GenTableColumn pkColumn;

    /** 子表信息 */
    private GenTable subTable;

    /** 表列信息 */
    @Valid
    private List<GenTableColumn> columns;

    /** 其它生成选项 */
    private String options;

    /** 树编码字段 */
    private String treeCode;

    /** 树父编码字段 */
    private String treeParentCode;

    /** 树名称字段 */
    private String treeName;

    /** 上级菜单ID字段 */
    private String parentMenuId;

    /** 上级菜单名称字段 */
    private String parentMenuName;

}
