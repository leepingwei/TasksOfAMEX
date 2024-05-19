import Pojo.Booking;
import Pojo.BookingDates;
import Pojo.BookingId;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.LocalDate;

import static io.restassured.RestAssured.*;

public class BookingTest {

    private Booking booking;
    private BookingDates bookingDates;
    private BookingId bookingId;
    private RequestSpecification reqSpec;
    private ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com/booking";
        reqSpec = given().log().all()
                .header("Content-type", "application/json")
                .contentType(ContentType.JSON);

        booking = new Booking();
        booking.setFirstname("Lee");
        booking.setLastname("Tester");
        booking.setTotalprice(168);
        booking.setDepositpaid(true);

        bookingDates = new BookingDates();
        LocalDate currentDate = LocalDate.now();
        bookingDates.setCheckin(currentDate.toString());
        bookingDates.setCheckout(currentDate.plusDays(10).toString());

        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("Paper and pen");

        bookingId = new BookingId();

    }

    @Test
    public void createBookingTest() {
        Response response = given()
                .spec(reqSpec)
                .body(booking)
                .when()
                .post();
        response.then().statusCode(200);
        bookingId.setBookingid(response.then().extract().path("bookingid"));
    }

    @Test(dependsOnMethods = "createBookingTest")
    public void getBookingTest(){
        Booking actualBooking = given()
                .spec(reqSpec)
                .when()
                .get("/" + bookingId.getBookingid())
                .then()
                .log().body()
                .extract().as(Booking.class);

        Assert.assertEquals(booking.getFirstname(), actualBooking.getFirstname());
        Assert.assertEquals(booking.getLastname(), actualBooking.getLastname());
        Assert.assertEquals(booking.getTotalprice(), actualBooking.getTotalprice());
        Assert.assertEquals(booking.isDepositpaid(), actualBooking.isDepositpaid());
        Assert.assertEquals(booking.getBookingdates().getCheckin(), actualBooking.getBookingdates().getCheckin());
        Assert.assertEquals(booking.getBookingdates().getCheckout(), actualBooking.getBookingdates().getCheckout());
        Assert.assertEquals(booking.getAdditionalneeds(), actualBooking.getAdditionalneeds());
    }
}
