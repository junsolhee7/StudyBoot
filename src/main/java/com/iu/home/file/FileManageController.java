package com.iu.home.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import com.iu.home.board.qna.QnaFileVO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class FileManageController extends AbstractView{
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			QnaFileVO qnaFileVO = (QnaFileVO)model.get("fileVO");
			log.info("------------------------------------");
			log.info("");
			
			File file = new File("C:/result/upload/qna/", qnaFileVO.getFileName());
			
			//한글 처리
			response.setCharacterEncoding("UTF-8");
			
			//총 파일의 크기
			response.setContentLengthLong(file.length());
			
			//다운로드시 파일의 이름을 인코딩
			String oriName = URLEncoder.encode(qnaFileVO.getOriName(),"UTF-8");
			
			//header 설정
			response.setHeader("Content-Disposition", "attachment;filename=\""+oriName+"\"");
			response.setHeader("Content-Transfer-Encoding", "binary");
			
			//HDD에서 파일을 읽고
			FileInputStream fi = new FileInputStream(file);
			
			//Client로 전송 준비
			OutputStream os = response.getOutputStream();
			
			//전송
			FileCopyUtils.copy(fi, os);
			
			//자원 해제(역순)
			os.close();
			fi.close();
	}
	
	@GetMapping("/fileDown/{path}")
	public ModelAndView fileDown(@PathVariable String path, QnaFileVO qnaFileVO) throws Exception{
		//DB에서 정보 조회
		ModelAndView mv = new ModelAndView();
		
		qnaFileVO.setFileName("img6.jpg");
		qnaFileVO.setOriName("img6.jpg");
		
		mv.addObject("fileVO",qnaFileVO);
		mv.addObject("path",path);
		mv.setViewName("fileManager");
		
		return mv;
	}
}
