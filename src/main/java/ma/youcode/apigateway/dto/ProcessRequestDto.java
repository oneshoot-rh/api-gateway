package ma.youcode.apigateway.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessRequestDto {
    @NotEmpty
    private List<String> technologies;
    @NotEmpty
    private List<String> experiences;
    @NotEmpty
    private List<String> locations;
    @NotEmpty
    private List<String> job_type;
    @NotEmpty
    private List<String> skills;
    @NotNull
    @NotBlank
    private String job_title;
    @NotNull
    @NotBlank
    private String job_description;
    @NotEmpty
    private List<String> related_industry;
    @NotNull
    @NotBlank
    private String availablity;
    @NotEmpty
    private List<String> degree_required;
    private Boolean check_github_projects;
    private Boolean check_references;
    private List<String> keywords;
}
