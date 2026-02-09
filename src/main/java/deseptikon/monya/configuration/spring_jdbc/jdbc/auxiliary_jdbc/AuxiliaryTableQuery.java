package deseptikon.monya.configuration.spring_jdbc.jdbc.auxiliary_jdbc;

import deseptikon.monya.configuration.spring_jdbc.models.CodeKLADR;

import java.util.List;

public interface AuxiliaryTableQuery {
    void insertCodeKLADRList(List<CodeKLADR> codeKLADRList);
    List<CodeKLADR> getListCodeKLADR();
    void fillRegexpKLADRList(List<CodeKLADR> codeKLADRList);
}
