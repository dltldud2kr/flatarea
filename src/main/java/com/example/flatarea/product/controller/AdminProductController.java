package com.example.flatarea.product.controller;

import com.example.flatarea.admin.service.BrandService;
import com.example.flatarea.admin.service.CategoryService;
import com.example.flatarea.product.model.ProductInput;
import com.example.flatarea.product.model.ProductParam;
import com.example.flatarea.product.dto.ProductDto;
import com.example.flatarea.product.service.ProductService;
import com.example.flatarea.util.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AdminProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final BrandService brandService;

    /**
     * 제품 목록
     */
    @GetMapping("/admin/product/list.do")
    public String list(Model model, ProductParam parameter) {
        parameter.init();
        List<ProductDto> productList = productService.list(parameter);

        long totalCount = 0;

        if(!CollectionUtils.isEmpty(productList)){
            totalCount = productList.get(0).getTotalCount();
        }
        String queryString  = parameter.getQueryString();

        PageUtil pageUtil = new PageUtil(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", productList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pageUtil.pager());

        return "admin/product/list";
    }

    /**
     * 제품 등록
     */

    /*
    @GetMapping("/admin/product/add.do")
    public String add(Model model, ProductInput parameter) {

        long id = parameter.getId();
        ProductDto existProduct = productService.getById(id);

        model.addAttribute("detail",existProduct);

        return "admin/product/add";
    }

    @GetMapping("/admin/product/edit.do")
    public String edit(Model model , ProductInput parameter) {

        long id = parameter.getId();
        ProductDto existProduct = productService.getById(id);

        if(existProduct == null){
            //에러처리
            model.addAttribute("message","제품정보가 존재하지 않습니다.");
            return "common/error";
        }

        return "admin/product/add";
    }

     */

    @GetMapping(value = {"/admin/product/add.do", "/admin/product/edit.do"})
    public String add(Model model , HttpServletRequest request ,
                       ProductInput parameter) {

        //카테고리 정보를 내려줘야 함.
        model.addAttribute("category",categoryService.list());
        model.addAttribute("brand",brandService.list());


        boolean editMode = request.getRequestURI().contains("/edit.do");
        ProductDto detail = new ProductDto();

        if(editMode){
            long id = parameter.getId();
            ProductDto existProduct = productService.getById(id);
            if(existProduct == null) {
                //에러처리
                model.addAttribute("message", "제품정보가 존재하지 않습니다.");
                return "common/error";
            }
            detail = existProduct;
        }
        model.addAttribute("detail", detail);
        model.addAttribute("editMode",editMode);
        return "admin/product/add";
    }

    @PostMapping(value = {"/admin/product/edit.do", "/admin/product/add.do"})
    public String addSubmit(Model model, HttpServletRequest request ,
                            ProductInput parameter, @RequestParam("file") MultipartFile file) {

        if (!file.isEmpty()){
            // 업로드된 파일의 이름
            String originalFilename = file.getOriginalFilename();
            // 파일이 저장될 경로
            String localPath = "C:/project/flatarea/files";
            // 새로운 파일 객체 생성
            File newFile = new File(localPath + File.separator + originalFilename);

            try {
                // 파일을 지정된 경로로 복사
                FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(newFile));
            } catch (IOException e){
                log.info("#################### - 1");
                log.info(e.getMessage());
            }

            // 파일이 저장된 경로를 데이터베이스에 저장
            String filePath = "/files/" + originalFilename; // 예를 들어 파일이 저장된 경로가 "/files/filename.jpg"라면
            System.out.println("================" + filePath + "==========");

            parameter.setImagePath(filePath); // ProductInput 클래스에 imagePath 필드가 있다고 가정합니다.
        }

        boolean editMode = request.getRequestURI().contains("/edit.do");

        if(editMode) {
            long id = parameter.getId();
            ProductDto existProduct = productService.getById(id);
            if (existProduct == null) {
                //에러처리
                model.addAttribute("message", "제품정보가 존재하지 않습니다.");
                return "common/error";
            }
            boolean result = productService.set(parameter);

        } else{
            boolean result = productService.add(parameter);
        }
        return "redirect:/admin/product/list.do";
    }

    @PostMapping("/admin/product/delete.do")
    public String del(Model model, HttpServletRequest request ,
                            ProductInput parameter) {

        boolean result = productService.del(parameter.getIdList());

        return "redirect:/admin/product/list.do";
    }

}