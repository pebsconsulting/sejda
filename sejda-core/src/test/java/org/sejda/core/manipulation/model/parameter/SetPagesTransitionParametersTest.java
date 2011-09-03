package org.sejda.core.manipulation.model.parameter;

import static org.mockito.Mockito.mock;

import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;
import org.sejda.core.TestUtils;
import org.sejda.core.manipulation.model.input.PdfSource;
import org.sejda.core.manipulation.model.input.PdfStreamSource;
import org.sejda.core.manipulation.model.output.TaskOutput;
import org.sejda.core.manipulation.model.pdf.transition.PdfPageTransition;
import org.sejda.core.manipulation.model.pdf.transition.PdfPageTransitionStyle;

public class SetPagesTransitionParametersTest {

    @Test
    public void testEquals() {
        SetPagesTransitionParameters victim1 = new SetPagesTransitionParameters();
        SetPagesTransitionParameters victim2 = new SetPagesTransitionParameters();
        SetPagesTransitionParameters victim3 = new SetPagesTransitionParameters();
        SetPagesTransitionParameters victim4 = new SetPagesTransitionParameters(PdfPageTransition.newInstance(
                PdfPageTransitionStyle.DISSOLVE, 1, 5), "name");
        TestUtils.testEqualsAndHashCodes(victim1, victim2, victim3, victim4);
    }

    @Test
    public void testPutTransition() {
        SetPagesTransitionParameters victim = new SetPagesTransitionParameters();
        PdfPageTransition firstTransition = PdfPageTransition.newInstance(PdfPageTransitionStyle.DISSOLVE, 1, 5);
        victim.putTransition(3, firstTransition);
        Assert.assertEquals(1, victim.getTransitions().size());
        PdfPageTransition secondTransition = PdfPageTransition.newInstance(PdfPageTransitionStyle.BOX_INWARD, 1, 2);
        PdfPageTransition result = victim.putTransition(3, secondTransition);
        Assert.assertEquals(firstTransition, result);
        Assert.assertEquals(1, victim.getTransitions().size());
    }

    @Test
    public void testInvalidParameters() {
        SetPagesTransitionParameters victim = new SetPagesTransitionParameters(null, "test.pdf");
        TaskOutput output = mock(TaskOutput.class);
        victim.setOutput(output);
        InputStream stream = mock(InputStream.class);
        PdfSource input = PdfStreamSource.newInstanceNoPassword(stream, "name");
        victim.setSource(input);
        TestUtils.assertInvalidParameters(victim);
    }
}
