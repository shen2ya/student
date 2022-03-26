package com.student.controller;

import com.arcsoft.face.ActiveFileInfo;
import com.arcsoft.face.AgeInfo;
import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.Face3DAngle;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FaceSimilar;
import com.arcsoft.face.FunctionConfiguration;
import com.arcsoft.face.GenderInfo;
import com.arcsoft.face.IrLivenessInfo;
import com.arcsoft.face.LivenessInfo;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectModel;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageInfo;
import com.arcsoft.face.toolkit.ImageInfoEx;
import com.student.entity.Student;
import com.student.entity.StudentExample;
import com.student.entity.StudentSign;
import com.student.mapper.StudentMapper;
import com.student.mapper.StudentSignMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.arcsoft.face.toolkit.ImageFactory.getGrayData;
import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${prop.baseFolder}")
    private String UPLOAD_FOLDER;
    private Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    private HttpServletRequest request;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    StudentSignMapper studentSignMapper;

    /**
     * 日期转换
     * @param date
     * @return
     */
    public static String DateToStringYYYYMM(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM");
        return formatter.format(date);
    }

    /**
     * 文件上传
     */
    @RequestMapping("/upload")
    public Map<String,Object> upload(MultipartFile file, String pid) {
        Map<String,Object> outEntity = new HashMap<String,Object>();
        try {
            byte[] bytes = file.getBytes();
            String fileName = UUID.randomUUID().toString();
            if(file.getOriginalFilename().contains(".")){
                fileName += "." + file.getOriginalFilename().split("\\.")[1];
            }
            String pre = pid + "/" + DateToStringYYYYMM(new Date()) + "/";

            Path path = Paths.get(UPLOAD_FOLDER + pre + fileName);
            //如果没有文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER + pre));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            Map<String,String> tip = new HashMap<>();
            tip.put("fileName",file.getOriginalFilename());
            tip.put("newName",pre + fileName);
            outEntity.put("tip", tip);
            outEntity.put("success", true);
            return outEntity;
        } catch (IOException e) {
            e.printStackTrace();
            outEntity.put("message", e.getMessage());
            outEntity.put("success", false);
            return outEntity;
        }
    }

    /**
     * 文件上传
     */
    @RequestMapping("/sign")
    public Map<String,Object> sign(MultipartFile file, Integer pid) {
        Map<String,Object> outEntity = new HashMap<String,Object>();
        //从官网获取
        String appId = "HATUCoJvPvASEMQNMApL2vo7t9ecNzr6JGWcvQsSi4Z8";
        String sdkKey = "FsyV7Qpg6SL4Vz1hZsnt9nZLZNGpfR8ytNn8qcHdBH7F";
        FaceEngine faceEngine = null;
        try {
            byte[] bytes = file.getBytes();
            String fileName = UUID.randomUUID().toString();
            if(file.getOriginalFilename().contains(".")){
                fileName += "." + file.getOriginalFilename().split("\\.")[1];
            }
            String pre = "temp/" + DateToStringYYYYMM(new Date()) + "/";

            Path path = Paths.get(UPLOAD_FOLDER + pre + fileName);
            //如果没有文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get(UPLOAD_FOLDER + pre));
            }
            //文件写入指定路径
            Files.write(path, bytes);

            faceEngine = new FaceEngine("D:\\Project\\self\\work\\student\\student\\student\\lib");
            //激活引擎
            int errorCode = faceEngine.activeOnline(appId, sdkKey);
            if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
                System.out.println("引擎激活失败");
            }
            ActiveFileInfo activeFileInfo=new ActiveFileInfo();
            errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
            if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
                System.out.println("获取激活文件信息失败");
            }

            //引擎配置
            EngineConfiguration engineConfiguration = new EngineConfiguration();
            engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
            engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
            engineConfiguration.setDetectFaceMaxNum(10);
            engineConfiguration.setDetectFaceScaleVal(16);
            //功能配置
            FunctionConfiguration functionConfiguration = new FunctionConfiguration();
            functionConfiguration.setSupportAge(true);
            functionConfiguration.setSupportFace3dAngle(true);
            functionConfiguration.setSupportFaceDetect(true);
            functionConfiguration.setSupportFaceRecognition(true);
            functionConfiguration.setSupportGender(true);
            functionConfiguration.setSupportLiveness(true);
            functionConfiguration.setSupportIRLiveness(true);
            engineConfiguration.setFunctionConfiguration(functionConfiguration);


            //初始化引擎
            errorCode = faceEngine.init(engineConfiguration);

            if (errorCode != ErrorInfo.MOK.getValue()) {
                System.out.println("初始化引擎失败");
            }

            //人脸检测
            ImageInfo imageInfo = getRGBData(new File(UPLOAD_FOLDER + pre + fileName));
            List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
            errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList);
            System.out.println(faceInfoList);

            //特征提取
            FaceFeature faceFeature = new FaceFeature();
            errorCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(), imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);
            System.out.println("特征值大小：" + faceFeature.getFeatureData().length);

            // get all student
            StudentExample studentExample = new StudentExample();
            studentExample.createCriteria().andValidEqualTo(1).andImgIsNotNull();
            List<Student> studentList = studentMapper.selectByExample(studentExample);

            for (Student stu:studentList) {
                //人脸检测2
                ImageInfo imageInfo2 = getRGBData(new File(UPLOAD_FOLDER + stu.getImg()));
                List<FaceInfo> faceInfoList2 = new ArrayList<FaceInfo>();
                errorCode = faceEngine.detectFaces(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(),imageInfo.getImageFormat(), faceInfoList2);
                System.out.println(faceInfoList);

                //特征提取2
                FaceFeature faceFeature2 = new FaceFeature();
                errorCode = faceEngine.extractFaceFeature(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(), imageInfo.getImageFormat(), faceInfoList2.get(0), faceFeature2);
                System.out.println("特征值大小：" + faceFeature.getFeatureData().length);

                //特征比对
                FaceFeature targetFaceFeature = new FaceFeature();
                targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
                FaceFeature sourceFaceFeature = new FaceFeature();
                sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
                FaceSimilar faceSimilar = new FaceSimilar();

                errorCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);

                System.out.println("score：" + faceSimilar.getScore());

               if(faceSimilar.getScore() >= 0.9){
                   StudentSign studentSign = new StudentSign();
                   studentSign.setStudentId(stu.getId());
                   studentSign.setSignType(pid);
                   studentSign.setSignTime(new Date());
                   studentSignMapper.insertSelective(studentSign);
                   outEntity.put("success", true);
                   //引擎卸载
                   errorCode = faceEngine.unInit();
                   return outEntity;
               }
            }
            //引擎卸载
            errorCode = faceEngine.unInit();
            outEntity.put("success", false);
            return outEntity;
        } catch (Exception e) {
            if(faceEngine != null) faceEngine.unInit();
            e.printStackTrace();
            outEntity.put("message", e.getMessage());
            outEntity.put("success", false);
            return outEntity;
        }
    }
}
