<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:output method="xml" indent="yes" />
    <xsl:template match="/">
      <xsl:apply-templates select="INVOICE" />
    </xsl:template>
    
    <xsl:template match="INVOICE">
    <Invoice xmlns="http://xml.schibsted.no/finance/invoice/v1">
        <Supplier>
            <Name>
                <xsl:value-of select="SUPPLIER" />
            </Name>
            <Email>
                <xsl:value-of select="SUPPLIER_EMAIL" />
            </Email>
        </Supplier>
        <Buyer>
            <Name>
                <xsl:value-of select="BUYER" />
            </Name>
            <Email>
                <xsl:value-of select="BUYER_EMAIL" />
            </Email>
        </Buyer>
    </Invoice>
    </xsl:template>
</xsl:stylesheet>