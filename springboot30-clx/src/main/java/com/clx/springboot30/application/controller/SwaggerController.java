package com.clx.springboot30.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@Tag(name = "SwaggerController", description = "Swagger测试Controller")
@RestController
public class SwaggerController {

    /**
     * http://localhost:3100/testSwagger?text=hello
     *
     * http://localhost:3100/swagger-ui/index.html
     * http://localhost:3100/v3/api-docs
     *
     * @param swaggerModel
     * @return
     */
    @Operation(summary = "测试swagger3用法", description = "具体详细描述swagger3用法",
            parameters = {@Parameter(name = "swaggerModel", description = "SwaggerModel实体")})
    @ApiResponse(responseCode = "200",description = "SwaggerModel实体的text属性")
    @GetMapping("/testSwagger")
    public String testSwagger(SwaggerModel swaggerModel) {
        return swaggerModel.getText();
    }

    @Data
    @Schema(name = "SwaggerModel", description = "SwaggerModel描述")
    public class SwaggerModel implements Serializable {
        @Schema(title = "测试文本text")
        private String text;
    }
}
