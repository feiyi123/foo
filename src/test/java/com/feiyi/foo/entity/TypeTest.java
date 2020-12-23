package com.feiyi.foo.entity;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by feiyi123 on 21/12/22.
 */
public class TypeTest {

    @Test
    public void testFromString() {
        String typeInt = "Integer";
        String typeFloat = "Float";
        Type resType = Type.fromString(typeInt);
        Assert.assertTrue("Expect  " + Type.INTEGER.toString() + ", but get "
                + resType.toString(), Type.fromString(typeInt).equals(Type.INTEGER));

        // 不存在的Type
        try {
            Type resTypeFloat = Type.fromString(typeFloat);
            Assert.assertTrue("Expect  get Error  , but get "
                    + resTypeFloat, false);
        } catch (Throwable e) {
            String res = "Type is invalid: " + typeFloat;
            Assert.assertTrue("Expect  " + res + "  , but get "
                    + e.getMessage(), res.equals(e.getMessage()));
        }
    }

    @Test
    public void testIsInteger() {
        String typeInt = "Integer";
        Type resType = Type.fromString(typeInt);
        boolean res = resType.isInteger();
        Assert.assertTrue("Expect  true, but get "
                + res, res);
    }
}
