package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.icet.dto.Order;
import edu.icet.entity.OrderEntity;
import edu.icet.repository.AccommodationRepository;
import edu.icet.repository.DayOutRepository;
import edu.icet.repository.MenuOptionsRepository;
import edu.icet.repository.OrderRepository;
import edu.icet.service.PlaceOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PlaceOrderServiceImpl implements PlaceOrderService {

    private final AccommodationRepository accommodationRepository;
    private final MenuOptionsRepository menuOptionsRepository;
    private final DayOutRepository dayOutRepository;
    private  final OrderRepository repository;
    private final ObjectMapper mapper;

    @Override
    @Transactional
    public void placeOrder(Order order) {

        if (order.getCategory().equals("Accommodations")){
            accommodationRepository.updateQuantity(order.getQty(),order.getPackageID());
            repository.save(mapper.convertValue(order, OrderEntity.class));
        }else if(order.getCategory().equals("MenuOptions")){
            menuOptionsRepository.updateQuantity(order.getQty(),order.getPackageID());
            repository.save(mapper.convertValue(order, OrderEntity.class));
        } else if (order.getCategory().equals("DayOut")) {
        dayOutRepository.updateQuantity(order.getQty(),order.getPackageID());
            repository.save(mapper.convertValue(order, OrderEntity.class));
        }

    }

    @Override
    public List<Order> getOrderByCustomer(String email) {
        List<Order> orderList=new ArrayList<>();
        List<OrderEntity> orderEntityList = repository.findByEmail(email);
        orderEntityList.forEach(orderEntity -> orderList.add(mapper.convertValue(orderEntity,Order.class)));

        return orderList;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orderList=new ArrayList<>();
        List<OrderEntity> all = repository.findAll();
        all.forEach(orderEntity -> orderList.add(mapper.convertValue(orderEntity, Order.class)));
        return orderList;
    }

    @Override
    public ByteArrayInputStream pdfReportGenerate() {
        List<OrderEntity> all = repository.findAll();
        Document document = new Document(PageSize.A4, 20, 20, 20, 20);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();
            Image logo = null;
            try {
                logo = Image.getInstance("img/hotel-logo.jpg");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            logo.scaleToFit(100, 50);
            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, BaseColor.DARK_GRAY);
            Paragraph title = new Paragraph("Order Details Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            document.add(title);
            PdfPTable table = new PdfPTable(9);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);
            table.setWidths(new int[]{2, 4, 2, 4, 2, 2, 2, 2, 2}); // Adjust column widths
            Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6, BaseColor.WHITE);
            BaseColor headerColor = new BaseColor(102, 178, 255); // Light blue color
            Stream.of("Order ID", "Customer Name", "Package ID", "Email", "Total", "Quantity", "Check-In", "Check-Out", "Category")
                    .forEach(columnTitle -> {
                        PdfPCell headerCell = new PdfPCell(new Phrase(columnTitle, headerFont));
                        headerCell.setBackgroundColor(headerColor);
                        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        headerCell.setPadding(8f);
                        table.addCell(headerCell);
                    });


            Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 7, BaseColor.BLACK);
            BaseColor rowColor = new BaseColor(224, 235, 255); // Light gray color for alternate rows

            boolean alternate = false;
            for (OrderEntity entity : all) {

                BaseColor cellBackground = alternate ? rowColor : BaseColor.WHITE;

                PdfPCell idCell = new PdfPCell(new Phrase(entity.getOrderID().toString(), cellFont));
                idCell.setBackgroundColor(cellBackground);
                idCell.setPadding(6f);
                table.addCell(idCell);

                PdfPCell nameCell = new PdfPCell(new Phrase(entity.getCustomerName(), cellFont));
                nameCell.setBackgroundColor(cellBackground);
                nameCell.setPadding(6f);
                table.addCell(nameCell);

                PdfPCell packageIDCell = new PdfPCell(new Phrase(entity.getPackageID().toString(), cellFont));
                packageIDCell.setBackgroundColor(cellBackground);
                packageIDCell.setPadding(6f);
                table.addCell(packageIDCell);

                PdfPCell emailCell = new PdfPCell(new Phrase(entity.getEmail(), cellFont));
                emailCell.setBackgroundColor(cellBackground);
                emailCell.setPadding(6f);
                table.addCell(emailCell);

                PdfPCell totalCell = new PdfPCell(new Phrase(entity.getTotal().toString(), cellFont));
                totalCell.setBackgroundColor(cellBackground);
                totalCell.setPadding(6f);
                table.addCell(totalCell);

                PdfPCell qtyCell = new PdfPCell(new Phrase(entity.getQty().toString(), cellFont));
                qtyCell.setBackgroundColor(cellBackground);
                qtyCell.setPadding(6f);
                table.addCell(qtyCell);

                PdfPCell checkInCell = new PdfPCell(new Phrase(entity.getCheckIn().toString(), cellFont));
                checkInCell.setBackgroundColor(cellBackground);
                checkInCell.setPadding(6f);
                table.addCell(checkInCell);

                PdfPCell checkOutCell = new PdfPCell(new Phrase(entity.getCheckOut().toString(), cellFont));
                checkOutCell.setBackgroundColor(cellBackground);
                checkOutCell.setPadding(6f);
                table.addCell(checkOutCell);

                PdfPCell categoryCell = new PdfPCell(new Phrase(entity.getCategory(), cellFont));
                categoryCell.setBackgroundColor(cellBackground);
                categoryCell.setPadding(6f);
                table.addCell(categoryCell);

                alternate = !alternate;
            }

            document.add(table);
            Font footerFont = FontFactory.getFont(FontFactory.HELVETICA, 8, BaseColor.GRAY);
            Paragraph footer = new Paragraph("Report generated on: " + new java.util.Date(), footerFont);
            footer.setAlignment(Element.ALIGN_RIGHT);
            footer.setSpacingBefore(10);
            document.add(footer);

            document.close();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
