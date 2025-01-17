<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <head>
                <title>Product Details</title>
            </head>
            <body>
                <h1>Product Information</h1>
                <p><strong>ID:</strong> <xsl:value-of select="Product/id"/></p>
                <p><strong>Name:</strong> <xsl:value-of select="Product/name"/></p>
                <p><strong>Price:</strong> <xsl:value-of select="Product/price"/></p>
                <h2>Warehouse Details</h2>
                <p><strong>Warehouse Name:</strong> <xsl:value-of select="Product/warehouse/name"/></p>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>