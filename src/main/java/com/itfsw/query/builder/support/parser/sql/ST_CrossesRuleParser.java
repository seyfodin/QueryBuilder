package com.itfsw.query.builder.support.parser.sql;

import com.itfsw.query.builder.support.model.IRule;
import com.itfsw.query.builder.support.model.enums.EnumDBType;
import com.itfsw.query.builder.support.model.enums.EnumOperator;
import com.itfsw.query.builder.support.model.sql.Operation;
import com.itfsw.query.builder.support.parser.AbstractSqlRuleParser;
import com.itfsw.query.builder.support.parser.JsonRuleParser;

public class ST_CrossesRuleParser extends AbstractSqlRuleParser {
    private final EnumDBType dbType;

    public ST_CrossesRuleParser(EnumDBType dbType) {
        this.dbType = dbType;
    }

    public boolean canParse(IRule rule) {
        return EnumOperator.ST_CROSSES.equals(rule.getOperator());
    }

    public Operation parse(IRule rule, JsonRuleParser parser) {
        if (dbType.equals(EnumDBType.SPATIALITE))
            return new Operation(new StringBuffer("ST_Crosses(").append(rule.getField()).append(", GeomFromEWKT(?)) = 1"), rule.getValue());
        else
            return new Operation(new StringBuffer("ST_Crosses(").append(rule.getField()).append(", GeomFromEWKT(?))"), rule.getValue());
    }
}
