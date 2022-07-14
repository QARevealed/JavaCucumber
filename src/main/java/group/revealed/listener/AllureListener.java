package group.revealed.listener;

import group.revealed.driver.DriverManager;
import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.StepResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Makes screenshots for Allure reporting
 */
public class AllureListener implements StepLifecycleListener {


    @Override
    public void afterStepUpdate(StepResult result) {
        //takes screen shot only if step is failed
/*        if (result.getStatus().equals(Status.FAILED) || result.getStatus().equals(Status.BROKEN)) {
            byte[] screenShot = getScreenShot();
            Allure.getLifecycle().addAttachment(result.getName(), "image/png", "png", screenShot);
        }*/
    }

    private byte[] getScreenShot() {
        Screenshot screenShot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .takeScreenshot(DriverManager.getDriver());
        BufferedImage originalImage = screenShot.getImage();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(originalImage, "png", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return outputStream.toByteArray();
    }
}
