package com.notificationsystem.fourqu.filegenerator;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.EncryptionConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.WriterProperties;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.AreaBreakType;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.notificationsystem.fourqu.model.Payment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
@Service
public class PDFGenerator implements Runnable{

    private List<Payment> payments;
    private String pdfFileName;
    private String password;

    public PDFGenerator() {
    }

    public PDFGenerator(List<Payment> paymentList, String pdfFileName , String password) {
        this.payments = paymentList;
        this.pdfFileName = pdfFileName;
        this.password = password;
    }

    public void generateStatement() throws IOException {

        final byte[] USERPASS = this.password.getBytes();
        final byte[] OWNERPASS = "owner".getBytes();

        // create font
//        String logoDir = "fourqu/src/main/resources/image/banklogo.png";
//        final String REGULAR = "fourqu/src/main/resources/font/NotoSans-Regular.ttf";
//        final String BOLD = "fourqu/src/main/resources/font/NotoSans-Bold.ttf";

        String logoDir = "/app/src/main/resources/image/banklogo.png";
        final String REGULAR = "/app/src/main/resources/font/NotoSans-Regular.ttf";
        final String BOLD = "/app/src/main/resources/font/NotoSans-Bold.ttf";

        PdfFont noto = PdfFontFactory.createFont(REGULAR);
        PdfFont notoBold = PdfFontFactory.createFont(BOLD);

        // pdf properties
        WriterProperties writerProperties = new WriterProperties();
        writerProperties.setStandardEncryption(USERPASS,OWNERPASS,
                EncryptionConstants.ALLOW_PRINTING, EncryptionConstants.ENCRYPTION_AES_128);

        // create pdf generator
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(this.pdfFileName,writerProperties));
        Document doc = new Document(pdfDoc);


        // page header
        // create logo image
        ImageData data = ImageDataFactory.create(logoDir);
        Image logo = new Image(data);
        logo.setHorizontalAlignment(HorizontalAlignment.CENTER);
        logo.setWidth(60);
        logo.setHeight(60);

        // create title
        Text subject = new Text("QU-PLUS e-Statement");
        subject.setFontSize(11);
        subject.setFont(notoBold);

        // page config
        int paymentNo = 0;
        float numRow = 20; // number of payment per page
        final int numPage = (int)Math.ceil(this.payments.toArray().length/numRow);

        for(int page = 0 ; page < numPage ; page++){
            // add page header
            doc.add(logo);
            doc.add(new Paragraph(subject).setTextAlignment(TextAlignment.CENTER));
            doc.add(new Paragraph(new Text("")));

            // create new table in current page
            Table table = new Table(new float[]{90,130,90,90}).setWidth(400);

            // table config
            int fontSize = 7;
            int margin = 2;

            // add row to table
            for (int i = 0 ; i < numRow ; i++) {
                if (paymentNo>=this.payments.toArray().length) break;
                if (i == 0) {
                    // add column header
                    table.addHeaderCell(new Cell().add(new Paragraph("Datetime")
                            .setFont(notoBold).setFontSize(fontSize).setMargin(margin)));
                    table.addHeaderCell(new Cell().add(new Paragraph("Description")
                            .setFont(notoBold).setFontSize(fontSize).setMargin(margin)));
                    table.addHeaderCell(new Cell().add(new Paragraph("Withdraw / Deposit")
                            .setFont(notoBold).setFontSize(fontSize).setMargin(margin)));
                    table.addHeaderCell(new Cell().add(new Paragraph("Outstanding Balance")
                            .setFont(notoBold).setFontSize(fontSize).setMargin(margin)));
                }
                // change datetime format
                String format = "dd/MM/yyyy HH:mm:ss";
                SimpleDateFormat dateFormat = new SimpleDateFormat(format);
                String date = dateFormat.format(this.payments.get(paymentNo).getDateTime());
                // add column data
                // when this row is not last row of page
                if( i != numRow-1 && paymentNo != this.payments.toArray().length - 1) {
                    table.addCell(new Cell().add(new Paragraph(date)
                                    .setFont(noto).setFontSize(fontSize).setMargin(margin))
                            .setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
                    table.addCell(new Cell().add(new Paragraph(this.payments.get(paymentNo).getDescription())
                                    .setFont(noto).setFontSize(fontSize).setMargin(margin))
                            .setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
                    table.addCell(new Cell().add(new Paragraph(String.valueOf(this.payments.get(paymentNo).getPaymentAmount()))
                                    .setFont(noto).setFontSize(fontSize).setMargin(margin))
                            .setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
                    table.addCell(new Cell().add(new Paragraph(String.valueOf(this.payments.get(paymentNo).getOutstandingBalance()))
                                    .setFont(noto).setFontSize(fontSize).setMargin(margin))
                            .setBorderBottom(Border.NO_BORDER).setBorderTop(Border.NO_BORDER));
                }
                // when this row is last row of page
                else {
                    table.addCell(new Cell().add(new Paragraph(date)
                                    .setFont(noto).setFontSize(fontSize).setMargin(margin))
                            .setBorderTop(Border.NO_BORDER));
                    table.addCell(new Cell().add(new Paragraph(this.payments.get(paymentNo).getDescription())
                                    .setFont(noto).setFontSize(fontSize)
                                    .setMargin(margin))
                            .setBorderTop(Border.NO_BORDER));

                    if (this.payments.get(paymentNo).getDescription().equals("transfer") ||
                            this.payments.get(paymentNo).getDescription().equals("withdraw")
                    )
                    {
                        table.addCell(new Cell().add(new Paragraph(String.valueOf(this.payments.get(paymentNo).getPaymentAmount()))
                                        .setFont(noto).setFontSize(fontSize)
                                        .setMargin(margin).setTextAlignment(TextAlignment.LEFT))
                                .setBorderTop(Border.NO_BORDER));
                    }
                    else {
                        table.addCell(new Cell().add(new Paragraph(String.valueOf(this.payments.get(paymentNo).getPaymentAmount()))
                                        .setFont(noto).setFontSize(fontSize)
                                        .setMargin(margin).setTextAlignment(TextAlignment.RIGHT))
                                .setBorderTop(Border.NO_BORDER));
                    }

                    table.addCell(new Cell().add(new Paragraph(String.valueOf(this.payments.get(paymentNo).getOutstandingBalance()))
                                    .setFont(noto).setFontSize(fontSize).setMargin(margin))
                            .setBorderTop(Border.NO_BORDER));
                }
                paymentNo++;
            }

            doc.add(table.setHorizontalAlignment(HorizontalAlignment.CENTER));
            // do not add new page if current page is last page.
            if(page != numPage-1) doc.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
        }

        doc.close();
        System.out.println("Statement created successfully...");
    }

    @Override
    public void run() {
        try {
            this.generateStatement();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
