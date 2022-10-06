package com.jitterted.ebp.blackjack;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class HexArchTest {

    @Test
    @Disabled
    public void domainMustNotDependOnAdapter() throws Exception {
        JavaClasses importedClasses =
                new ClassFileImporter().importPackages("com.jitterted.ebp.blackjack");

        ArchRule myRule = noClasses().that().resideInAPackage("..domain..")
                                     .should().dependOnClassesThat()
                                     .resideInAPackage("..adapter..");

        myRule.check(importedClasses);
    }
}
