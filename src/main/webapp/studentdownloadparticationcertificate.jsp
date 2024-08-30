<%@page import="com.klef.ep.models.Student"%>
<%
String studentName = request.getParameter("studentName");
String eventName = request.getParameter("eventName");
String eventid = request.getParameter("eid");
String event = "FOR EVENT : "+eventName;
%>
<!DOCTYPE html>
<html>
<head>
<title>Download Certificate</title>
<script type="text/javascript" src="https://unpkg.com/@pdf-lib/fontkit@0.0.4"></script>
<script type="text/javascript" src="https://unpkg.com/pdf-lib@1.4.0"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/FileSaver.js/2.0.5/FileSaver.min.js"></script>
<script type="text/javascript">
const studentName = "<%=studentName%>";
const eventName = "<%=event%>";

const { PDFDocument, rgb } = PDFLib;

const generatePDF = async (name, event) => {
    const existingPdfBytes = await fetch("/EPProject/resources/certificates/CERTIFICATETEMPLATE1.pdf").then((res) => res.arrayBuffer());

    const pdfDoc = await PDFDocument.load(existingPdfBytes);
    pdfDoc.registerFontkit(fontkit);

    const fontBytes1 = await fetch("/EPProject/resources/fonts/Alegreya-Italic-VariableFont_wght.ttf").then((res) => res.arrayBuffer());
    const fontBytes2 = await fetch("/EPProject/resources/fonts/WorkSans-VariableFont_wght.ttf").then((res) => res.arrayBuffer());

    const font1 = await pdfDoc.embedFont(fontBytes1);
    const font2 = await pdfDoc.embedFont(fontBytes2);

    const pages = pdfDoc.getPages();
    const firstPage = pages[0];

    const textSize1 = 58;
    const textSize2 = 15;

    const textWidth1 = font1.widthOfTextAtSize(name, textSize1);
    const textWidth2 = font2.widthOfTextAtSize(event,textSize2);

    const textHeight1 = textSize1;
    const textHeight2 = textSize2;
    
    const { width, height } = firstPage.getSize();
    
    const x = (width - textWidth1) / 2;
    const y = (height - textHeight1) / 2 + textHeight1;
    
    const x1 = (width - textWidth2) / 2;
    const y1 = (height - textHeight2) / 2 + textHeight2; 

    firstPage.drawText(name, {
        x: x,
        y: 270,
        size: textSize1,
        font: font1,
        color: rgb(1, 0.834, 0),
    });

    firstPage.drawText(event, {
        x: x1,
        y: 240,
        size: 15,
        font: font2,
        color: rgb(1, 1, 1), // Gold color in the 0-1 range
    });


    const pdfBytes = await pdfDoc.save();

    const file = new File(
        [pdfBytes],
        "<%=studentName%>_Certificate.pdf",
        {
            type: "application/pdf;charset=utf-8",
        }
    );
    saveAs(file);
};

generatePDF(studentName, eventName);
</script>
</head>
<body align="center" >
<h3 align="center" style="color: green;" >Certificate Downloading Started. If not Refresh Page</h3>
<a href="studentvieweventbyid.jsp?eid=<%=eventid %>"> Go Back</a>
</body>
</html>
