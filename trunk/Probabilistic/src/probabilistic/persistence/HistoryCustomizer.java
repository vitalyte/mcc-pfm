/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package probabilistic.persistence;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.history.HistoryPolicy;



/**
 *
 * @author Vitaly Brevus
 */
public class HistoryCustomizer implements DescriptorCustomizer{

    @Override
    public void customize(ClassDescriptor descriptor) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet.");
        HistoryPolicy policy = new HistoryPolicy();
//        policy.addHistoryTableName("SEMIELLIPTICALCRACK", "CRACK_HIST");
        policy.addHistoryTableName("CRACK_HIST");
        policy.addHistoryTableName("SEMIELLIPTICALCRACK_POINT", "POINT_HIST");
        policy.addStartFieldName("START_DATE");
        policy.addEndFieldName("END_DATE");
        descriptor.setHistoryPolicy(policy);

    }

}
